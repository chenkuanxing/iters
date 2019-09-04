package com.xinghui.security;
import com.xinghui.enums.HttpCodeEnum;
import com.xinghui.exception.CustException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @Author chenkuanxing
 * @Date 2019/6/21
 * security工具类
 */

@Log4j2
public class SecurityUtils {

    private static CustUserDetails userDetails;

    /**
     * 获取登录的用户Id
     *
     * @return
     */
    public static String getCurrentUserId() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (object != "anonymousUser") {
            userDetails = (CustUserDetails) object;
            return userDetails.getId();
        } else {
            throw new CustException(HttpCodeEnum.deny.getValue(), HttpCodeEnum.deny.getDesc());
        }
    }
    /**
     * 获取登录用户
     *
     * @return
     */
    public static CustUserDetails getCurrentUser() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (object != "anonymousUser") {
            userDetails = (CustUserDetails) object;
            return userDetails;
        } else {
            throw new CustException(HttpCodeEnum.deny.getValue(), HttpCodeEnum.deny.getDesc());
        }
    }
    /**
     * 获取用户角色权限
     * @return
     */
    public static List<String> getCurrentUserRoles(){
        Collection<? extends GrantedAuthority> authorities =  SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> permissions = new ArrayList<>();
        if (!CollectionUtils.isEmpty(authorities)){
            for (GrantedAuthority grantedAuthority : authorities){
                permissions.add(grantedAuthority.getAuthority());
            }
        }
        return permissions;
    }

}


