package us.codecraft.blackhole.suite.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import us.codecraft.blackhole.suite.util.IPUtils;

import java.io.IOException;

/**
 * User: cairne
 * Date: 13-5-12
 * Time: 下午7:40
 */
@Controller
@RequestMapping("help")
public class HelpController extends MultiActionController {

    @RequestMapping("dns")
    public ModelAndView dnsHelp() throws IOException {
        String localIp = IPUtils.getLocalIP();
        ModelAndView modelAndView = new ModelAndView("help-dns");
        modelAndView.addObject("localIp", localIp);
        return modelAndView;
    }

}
