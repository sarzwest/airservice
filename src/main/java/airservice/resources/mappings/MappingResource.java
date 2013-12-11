/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.resources.mappings;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
@XmlRootElement(name = "resource")
public class MappingResource {

    private String name;
    private String url;

    public MappingResource(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public MappingResource() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
