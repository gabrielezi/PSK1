package lt.vu.services;

import lombok.Getter;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.Map;

@RequestScoped
public class ParameterCollector {
    @Getter
    private Map<String, String> params = null;

    public void collectParams(){
        if (params == null)
            params =  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    }

    public String get(String parameterName){
        collectParams();
        return params.get(parameterName);
    }

    public int getInt(String key){
        collectParams();
        return Integer.parseInt(params.get(key));
    }
}
