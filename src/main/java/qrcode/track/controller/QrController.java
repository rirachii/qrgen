package qrcode.track.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qrcode.track.service.QrService;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/v1")
public class QrController {

    @RequestMapping(value = "/custom", method = RequestMethod.GET)
    public void generateQr(
            @RequestParam("data") String data,
            @RequestParam(name="format", defaultValue = "png") String format,
            @RequestParam(name="download", defaultValue = "true") String download,
            @RequestParam(name="scale", defaultValue = "10") String scale,
            @RequestParam(name="lightColor", defaultValue = "") String lightColor,
            @RequestParam(name="darkColor", defaultValue = "") String darkColor,
            HttpServletResponse response
            ) throws IOException
    {

        String fileName = URLEncoder.encode(data, StandardCharsets.UTF_8) + "." + format;
        response.setContentType("image/" + format);
        if (download.equalsIgnoreCase("true"))
        {
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
        }
        QrService.generateQR(data, format, scale);
        Path file = Paths.get("c:\\qrcodes\\" + fileName);
        try
        {
            Files.copy(file, response.getOutputStream());
            response.getOutputStream().flush();
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}

//model is the return statement or any model classes that organize and process data return
//Controller here is the greetcontroller which match greeting statement to a given route /greetings
//RestController combines the @Controller and @ResponseBody, allows us to just return string data
