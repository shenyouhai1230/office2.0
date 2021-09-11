 /**
 * 角色权限处理
 * Copyright (c) 2019 office
 */
 
import store from '@/store'

export default {
  inserted(el, binding, vnode) {
    const { value } = binding
    const super_admin = "admin";
    const roles = store.getters && store.getters.roles
    if (value && value instanceof Array && value.length > 0) {
      const roleFlag = value
      const hasRole = roles.some(role => {
        return super_admin === role || roleFlag.includes(role)
      })
      if (!hasRole) {
        // el.parentNode && el.parentNode.removeChild(el)
        // el.setAttribute("disabled","disabled");
        // el.parentNode.removeChild(el);
        el.classList.add("is-disabled");
        console.log(el.children[0].readOnly);
        el.children[0].readOnly = true;

      }
    } else {
      throw new Error(`请设置角色权限标签值"`)
    }
  }
}
