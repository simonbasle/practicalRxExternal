package org.dogepool.external.avatar;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/avatar")
public class AvatarApp {

    @ResponseBody
    @RequestMapping(value = {"{id}", "{id}/small"}, method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] avatar(@PathVariable long id, HttpServletRequest request) throws IOException {
        int avatarNumber = (int) id % 8;
        if (avatarNumber == 0)
            avatarNumber = 8;
        String avatarFile = "/images/avatar" + avatarNumber + ".png";
        InputStream in = this.getClass().getResourceAsStream(avatarFile);
        return IOUtils.toByteArray(in);
    }

}
