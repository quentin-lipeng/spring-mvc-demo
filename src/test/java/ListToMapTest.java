import org.junit.jupiter.api.Test;
import org.quentin.web.dto.WebResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author quentin
 */
public class ListToMapTest {
    @Test
    public void toMap() {
        List<WebResource> resources = new ArrayList<>();
        resources.add(new WebResource("resource", "/resource"));
        resources.add(new WebResource("login", "/login"));

        Map<String, String> map = resources.stream().collect(Collectors.toMap(WebResource::getResourceName, WebResource::getResourceInfo));

        map.forEach((k, v) -> {
            System.out.println(k + " " + v);
        });

    }
}
