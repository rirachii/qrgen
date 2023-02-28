package qrcode.track.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/*
 * This controller will work as an url shortener.
 * Takes in short url and redirects user to long url.
 * In order to track analytics, this is needed.
 */

@RestController
public class HtmlController {
    @GetMapping(value = "/")
    public String homePage() {
//      Call db with id to get long url, ? create a method in service to do this
        String longUrl;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>\n<head>\n");
        stringBuilder.append("<!-- Google Tag Manager -->");
        stringBuilder.append("<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':\n");
        stringBuilder.append("new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],");
        stringBuilder.append("j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=");
        stringBuilder.append("'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);\n");
        stringBuilder.append("})(window,document,'script','dataLayer','GTM-WQ5JWLN');</script>");
        stringBuilder.append("<!-- End Google Tag Manager -->");
        stringBuilder.append("</head>\n");
        stringBuilder.append("<body>\n<!-- Google Tag Manager (noscript) -->\n");
        stringBuilder.append("<noscript><iframe src=\"https://www.googletagmanager.com/ns.html?id=GTM-WQ5JWLN\"");
        stringBuilder.append("height=\"0\" width=\"0\" style=\"display:none;visibility:hidden\"></iframe></noscript>");
        stringBuilder.append("<!-- End Google Tag Manager (noscript) -->");
        stringBuilder.append("</body>\n");
        stringBuilder.append("</html>\n");
        return stringBuilder.toString();
    }

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String redirect(@PathVariable("id") String id) {
//      Call db with id to get long url, ? create a method in service to do this
        String longUrl;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>\n<header>\n");
        stringBuilder.append("<script async src=\"https://www.googletagmanager.com/gtag/js?id=G-4PKE5HDNLG\"></script>\n");
        stringBuilder.append("<script>\nwindow.dataLayer = window.dataLayer || []\n");
        stringBuilder.append("function gtag(){ dataLayer.push(arguments); }\n");
        stringBuilder.append("gtag('js', new Date());\n");
        stringBuilder.append("gtag('config', 'G-4PKE5HDNLG')\n");
        stringBuilder.append("</script>\n");
        stringBuilder.append("<meta http-equiv=\"refresh\" content=\"1; url='");
        stringBuilder.append("https://www.mweng.me"); // pass in longUrl here
        stringBuilder.append("'\"/>\n</header>\n</html>\n");
        return stringBuilder.toString();
    }

    @GetMapping({"/home", "/status"})
    public String getStatus() {
        return "Application is up and running";
    }
}
