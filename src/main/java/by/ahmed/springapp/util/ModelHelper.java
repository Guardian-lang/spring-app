package by.ahmed.springapp.util;

import by.ahmed.springapp.filter.AuthorFilter;
import lombok.experimental.UtilityClass;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@UtilityClass
public class ModelHelper {
    public static void addAttributes(Model model, Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            model.addAttribute(key, value);
        }
    }

    public static void redirectAttributes(RedirectAttributes redirectAttributes,
                                          Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            redirectAttributes.addFlashAttribute(key, value);
        }
    }
}
