import { defineStore } from "pinia";

export const useUserDetailsStore = defineStore("userDetails", {
    state: () => ({
        id: 1,
        nickname: null,
        email: null,
        roles: []
    }),
    getters: {
        isAuthenticated: (state) => state.email == null ? false : true

    },
    actions: {
        logout() {
            this.nickname = null;
            this.email = null;
            this.id = 0;
            this.roles= [];
            localStorage.removeItem('id');
            localStorage.removeItem('email');
            localStorage.removeItem('nickname');
            // 로컬 저장소에서 roles 값 제거 (선택적으로 필요한 경우에만 사용)
            // localStorage.removeItem('roles');
        },

        hasRole(role) {
            let result = this.roles.indexOf(role) < 0 ? false : true;
            return result;
        }
    },
    persist: {
        enabled: true,
        strategies: [
            { storage: localStorage, paths: ['id', 'email', 'nickname','roles'] },
        ],
    },
});
