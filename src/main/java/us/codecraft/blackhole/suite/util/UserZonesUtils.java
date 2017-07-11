package us.codecraft.blackhole.suite.util;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User: cairne
 * Date: 13-5-26
 * Time: 下午4:19
 */
public class UserZonesUtils {

    public static class DomainConfig {
        private String domain;
        private boolean active;
        private String ip;
        private String comment;

        public DomainConfig() {
        }

        public DomainConfig(String domain, String ip, boolean active) {
            this.active = active;
            this.domain = domain;
            this.ip = ip;
        }

        public DomainConfig(String domain, String ip, String comment, boolean active) {
            this.active = active;
            this.domain = domain;
            this.comment = comment;
            this.ip = ip;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }
    }

    public static String fromJson(String json) throws IOException {
        StringBuilder sb = new StringBuilder();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> list = (List<Map<String, Object>>) objectMapper.readValue(json, List.class);
        for (Map<String, Object> objectMap : list) {
            List<Map> config = (List<Map>) objectMap.get("config");
            for (Map domainConfig : config) {
                sb.append(((Boolean) domainConfig.get("active") ? "" : "#") + domainConfig.get("ip") + "\t" + domainConfig.get("domain") + (domainConfig.get("comment") != null && ((String) domainConfig.get("comment")).trim().length() > 0 ? "\t#" + domainConfig.get("comment") : "") + "\n");
            }
        }
        return sb.toString().trim();
    }

    public static String toJson(String text) throws IOException {
        if (StringUtils.isBlank(text)) {
            return "[]";
        }
        Map<String, List<DomainConfig>> map = new LinkedHashMap<String, List<DomainConfig>>();
        String[] lines = text.split("\n");
        for (String line : lines) {
            boolean isActive = true;
            line = StringUtils.trim(line);
            if (StringUtils.startsWith(line, "#")) {
                isActive = false;
                line = line.replaceAll("^#+", "");
            }
            String[] tokens = line.split("\\s+");
            if (tokens.length > 1) {
                String ip = tokens[0];
                String comment = null;
                //scan for commet
                for (String token : tokens) {
                    if (token.startsWith("#")) {
                        comment = token.replaceAll("^#+", "");
                        break;
                    }
                }

                for (int i = 1; i < tokens.length; i++) {
                    String domain = tokens[i];
                    if (domain.startsWith("#")) {
                        continue;
                    }
                    List<DomainConfig> domainConfigs = map.get(domain);
                    if (domainConfigs == null) {
                        domainConfigs = new ArrayList<DomainConfig>();
                        map.put(domain, domainConfigs);
                    }
                    domainConfigs.add(new DomainConfig(domain, ip, comment, isActive));
                }
            }
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Map.Entry<String, List<DomainConfig>> listEntry : map.entrySet()) {
            Map<String, Object> innerMap = new LinkedHashMap<String, Object>();
            innerMap.put("domain", listEntry.getKey());
            innerMap.put("config", listEntry.getValue());
            list.add(innerMap);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        return objectMapper.writeValueAsString(list);
    }

    public static String merge(String textA, String textB) {
        if (StringUtils.isBlank(textA) && StringUtils.isBlank(textB)) {
            return "";
        }
        if (StringUtils.isBlank(textA)) {
            return textB;
        }
        if (StringUtils.isBlank(textB)) {
            return textA;
        }
        String[] linesA = textA.split("\n");
        String[] linesB = textB.split("\n");
        // domain-ip-active
        DoubleKeyMap<String, String, DomainConfig> linesMap = new DoubleKeyMap<String, String, DomainConfig>(new LinkedHashMap<String, Map<String, DomainConfig>>(), LinkedHashMap.class);
        for (String line : linesA) {
            extractLine(linesMap, line);
        }
        for (String line : linesB) {
            extractLine(linesMap, line);
        }
        StringBuilder sb = new StringBuilder();
        //only one config can be active for a domain
        for (Map.Entry<String, Map<String, DomainConfig>> mapEntry : linesMap.entrySet()) {
            boolean hasOneActive = false;
            for (Map.Entry<String, DomainConfig> entry : mapEntry.getValue().entrySet()) {
                if (entry.getValue().isActive()) {
                    if (!hasOneActive) {
                        hasOneActive = true;
                    } else {
                        entry.getValue().setActive(false);
                    }
                }
            }
        }

        for (Map.Entry<String, Map<String, DomainConfig>> mapEntry : linesMap.entrySet()) {
            for (Map.Entry<String, DomainConfig> entry : mapEntry.getValue().entrySet()) {
                sb.append((entry.getValue().isActive() ? "" : "#") + entry.getKey() + "\t" + mapEntry.getKey() +
                        (!StringUtils.isBlank(entry.getValue().getComment()) ? "\t#" + entry.getValue().getComment() : "") + "\n");
            }
        }
        return sb.toString().trim();
    }

    private static void extractLine(DoubleKeyMap<String, String, DomainConfig> linesMap, String line) {
        boolean isActive = true;
        String comment = null;
        line = StringUtils.trim(line);
        if (StringUtils.startsWith(line, "#")) {
            isActive = false;
            line = line.replaceAll("^#+", "");
        }
        String[] tokens = line.split("\\s+");
        if (tokens.length > 1) {
            String ip = tokens[0];
            for (String token : tokens) {
                if (token.startsWith("#")) {
                    comment = token.replaceAll("^#+", "");
                    break;
                }
            }
            for (int i = 1; i < tokens.length; i++) {
                String domain = tokens[i];
                if (domain.startsWith("#")) {
                    continue;
                }
                if (linesMap.get(domain, ip) == null) {
                    linesMap.put(domain, ip, new DomainConfig(domain, ip, comment, isActive));
                } else {
                    if ((!linesMap.get(domain, ip).isActive() && isActive) || (StringUtils.isBlank(linesMap.get(domain, ip).getComment()) && StringUtils.isNotBlank(comment))) {
                        linesMap.put(domain, ip, new DomainConfig(domain, ip, comment, isActive));
                    }
                }
            }
        }
    }

}
