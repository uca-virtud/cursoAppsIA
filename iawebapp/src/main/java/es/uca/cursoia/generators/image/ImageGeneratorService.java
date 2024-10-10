package es.uca.cursoia.generators.image;

import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.image.ImageModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageGeneratorService {

    ImageModel imageModel;
    @Autowired
    private HttpServletRequest request;


    public ImageGeneratorService(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    public String getServerUrl() {
        String serverUrl = request.getRequestURL().toString();
        return serverUrl;
    }

    public Image generate(String prompt) {
        return imageModel.generate(prompt).content();
    }


}
