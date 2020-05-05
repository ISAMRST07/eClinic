import Vue from 'vue';
import router from '../router/index'
const tokenResponse = JSON.parse(localStorage.getItem('tokenResponse'));
let initialExpiresIn = 0;
let initialToken = '';
let initialUser = null;
let initialRole = '';
if (tokenResponse) {
    initialExpiresIn = tokenResponse.expiresIn;
    if (initialExpiresIn > 0) {
        initialToken = tokenResponse.accessToken;
        initialUser = tokenResponse.loggedUser;
        initialRole = initialUser.type;
    }
}


const AuthModule = {
    namespaced: true,
    state: {
        token: initialToken,
        expiresIn: initialExpiresIn,
        user: initialUser,
        role: initialRole,
    },
    mutations: {
        updateResponse(state, tokenResponse) {
            state.token = tokenResponse.accessToken;
            state.expiresIn = tokenResponse.expiresIn;
            state.user = tokenResponse.loggedUser;
            state.role = tokenResponse.loggedUser.type;
        },
        clearResponse(state) {
            state.token = '';
            state.expiresIn = 0;
            state.user = null;
            state.role = '';
        }
    },
    getters: {
        loggedIn: state => !!state.user
    },
    actions: {
        login({state, commit}, authRequest) {
            Vue.prototype.$axios.post('/api/auth/login', authRequest).then(res => {
                localStorage.setItem('tokenResponse', JSON.stringify(res.data));
                commit('updateResponse', res.data);
            }).catch(err => {
                console.error(err);
            })
        },
        logout({commit}) {
            localStorage.removeItem('tokenResponse');
            commit('clearResponse');
            router.push('/login');
        }
    }
};

export default AuthModule;

export function redirectToLogin(commit) {
    localStorage.removeItem('tokenResponse');
    commit('auth/clearResponse', {root: true});
    router.push('/login');
}
