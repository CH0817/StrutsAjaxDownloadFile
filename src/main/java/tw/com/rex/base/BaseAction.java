package tw.com.rex.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Rex on 2017/05/09.
 */
@SuppressWarnings("unused")
public abstract class BaseAction<T> extends ActionSupport
        implements ModelDriven<T>, SessionAware, ServletResponseAware, ServletRequestAware {

    private T model;
    protected Map<String, Object> session;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    @SuppressWarnings("unchecked")
    public BaseAction() {
        // try {
        //     ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        //     Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
        //     model = clazz.newInstance();
        // } catch (Exception e) {
        //     throw new RuntimeException(e);
        // }
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    @Override
    public T getModel() {
        return model;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

}
