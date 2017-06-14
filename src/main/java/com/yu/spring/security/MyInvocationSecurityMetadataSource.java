package com.yu.spring.security;

import com.yu.spring.dao.MenuDao;
import com.yu.spring.dao.PrivilegeDao;
import com.yu.spring.entity.Menu;
import com.yu.spring.entity.Privilege;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2017/6/12.
 */
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource , InitializingBean {

    protected final Log logger = LogFactory.getLog(getClass());
    private final static String                              RES_KEY               = "resourcePath";
    private final static String                              AUTH_KEY              = "authorityMark";
    private final static List<ConfigAttribute> NULL_CONFIG_ATTRIBUTE = Collections.emptyList();
    //权限集合
    private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;
    public final static Map<String, List<Privilege>>       arr                   = new HashMap<String, List<Privilege>>();
    @Autowired
    private PrivilegeDao privilegeDao;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        final HttpServletRequest request = ((FilterInvocation) o).getRequest();
        Collection<ConfigAttribute> attrs = NULL_CONFIG_ATTRIBUTE;
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                attrs = entry.getValue();
                break;
            }
        }
        logger.info("URL资源：" + request.getRequestURI() + " -> " + attrs.toString());
        return attrs;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }
        return allAttributes;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

    private Map<String, String> loadResuorce() {
        Map<String, String> map = new LinkedHashMap<String, String>();

        List<Map<String, String>> list = this.getURLResourceMapping();
        Iterator<Map<String, String>> it = list.iterator();
        while (it.hasNext()) {
            Map<String, String> rs = it.next();
            String resourcePath = rs.get(RES_KEY);// "resourcePath"
            String authorityMark = rs.get(AUTH_KEY);// "authorityMark"
            if (map.containsKey(resourcePath)) {
                String mark = map.get(resourcePath);
                map.put(resourcePath, mark + "," + authorityMark);
            } else {
                map.put(resourcePath, authorityMark);
            }
        }
        return map;
    }

    @Autowired
    private MenuDao menuDao;

    public List<Map<String, String>> getURLResourceMapping() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        List<Menu> menuDoList = null;//所有菜单列表
        try {
            menuDoList = menuDao.queryMenu();
            for (int i = 0; menuDoList != null && i < menuDoList.size(); i++) {
                Menu info = menuDoList.get(i);
                if (info.getUrl() != null && !"".equals(info.getUrl())) {
                    Map<String, String> map = new HashMap<String, String>();
                    if (!info.getUrl().startsWith("/")) {
                        info.setUrl("/" + info.getUrl());
                    }
                    map.put("resourcePath", info.getUrl());
                    map.put("authorityMark", info.getMaker());
                    list.add(map);
                    //添加菜单url
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //资源列表
        List<Privilege> privilegeDoList = null;
        try {
            privilegeDoList = this.privilegeDao.queryPrivilege();
            for (int i = 0; privilegeDoList != null && i < privilegeDoList.size(); i++) {
                Privilege info = privilegeDoList.get(i);
                if (info.getUrl() != null && !"".equals(info.getUrl())) {

                    String[] urls = info.getUrl().split(";");
                    if (urls != null) {
                        for (String url : urls) {
                            Map<String, String> map = new HashMap<String, String>();
                            if (!url.startsWith("/")) {
                                url = "/" + url;
                            }
                            map.put("resourcePath", url);
                            map.put("authorityMark", info.getMark());
                            list.add(map);
                            //添加资源url
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //循环遍历菜单列表
        //遍历资源列表
        for (int i = 0; menuDoList != null && i < menuDoList.size(); i++) {
            if (menuDoList.get(i).getUrl() != null) {
                List<Privilege> rr = new ArrayList<>(menuDoList.get(i).getPrivileges());
                if(arr.get(menuDoList.get(i).getUrl()) == null)
                {
                    arr.put(menuDoList.get(i).getUrl(),rr);
                }
                else
                {
                    rr.addAll(arr.get(menuDoList.get(i).getUrl()));
                    arr.put(menuDoList.get(i).getUrl(),rr);
                }
               /* for (int j = 0; privilegeDoList != null && j < privilegeDoList.size(); j++) {
                    //如果当前资源j属于当前菜单i下
                    if (menuDoList.get(i).getId().equals(privilegeDoList.get(j).getMenuId())) {
                        //如果菜单i没有url,则添加资源j
                        if (arr.get(menuDoList.get(i).getUrl()) == null) {
                            List<Privilege> rr = new ArrayList<Privilege>();
                            rr.add(privilegeDoList.get(j));
                            arr.put(menuDoList.get(i).getUrl(), rr);
                        } else {
                            //如果菜单有url,则添加资源j和菜单url
                            List<Privilege> rr = arr.get(menuDoList.get(i).getUrl());
                            boolean b = true;
                            //资源j是否在rr中
                            for (Privilege p : rr) {
                                if (p.getId().equals(privilegeDoList.get(j).getId())) {
                                    b = false;
                                    break;
                                }

                            }
                            if (b) {
                                rr.add(privilegeDoList.get(j));
                            }
                            arr.remove(menuDoList.get(i).getUrl());
                            arr.put(menuDoList.get(i).getUrl(), rr);
                        }
                    }
                }*/

            }
        }
        return list;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.requestMap = this.bindRequestMap();
    }


    protected Map<RequestMatcher, Collection<ConfigAttribute>> bindRequestMap() {
        Map<RequestMatcher, Collection<ConfigAttribute>> map = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
        Map<String, String> resMap = this.loadResuorce();
        for (Map.Entry<String, String> entry : resMap.entrySet()) {
            String key = entry.getKey();
            Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
            logger.debug("key:" + key + "value:" + entry.getValue());
            atts = SecurityConfig.createListFromCommaDelimitedString(entry.getValue());
            map.put(new AntPathRequestMatcher(key), atts);
        }
        return map;
    }
}
