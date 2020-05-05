import Vue from 'vue';
import router from '../router/index'
const tokenResponse = JSON.parse(localStorage.getItem('tokenResponse'));
const loginState = {
    NOT_LOGGED: 0,
    LOGGING: 1,
    LOGGED: 2
}
let initialExpiresIn = 0;
let initialToken = '';
let initialUser = null;
let initialRole = '';
let initialLoggedIn = loginState.NOT_LOGGED;
if (tokenResponse) {
    initialExpiresIn = tokenResponse.expiresIn;
    if (initialExpiresIn > 0) {
        initialToken = tokenResponse.accessToken;
        initialUser = tokenResponse.loggedUser;
        initialRole = initialUser.type;
        initialLoggedIn = loginState.LOGGED;
    }
}


const AuthModule = {
    namespaced: true,
    state: {
        token: initialToken,
        expiresIn: initialExpiresIn,
        user: initialUser,
        role: initialRole,
        loggedIn: initialLoggedIn
    },
    mutations: {
        updateResponse(state, tokenResponse) {
            state.token = tokenResponse.accessToken;
            state.expiresIn = tokenResponse.expiresIn;
            state.user = tokenResponse.loggedUser;
            state.role = tokenResponse.loggedUser.type;
            state.loggedIn = loginState.LOGGED;
        },
        clearResponse(state) {
            state.token = '';
            state.expiresIn = 0;
            state.user = null;
            state.role = '';
            state.loggedIn = loginState.NOT_LOGGED;
        },
        logging(state, currentState) {
            state.loggedIn = currentState;
        }
    },
    getters: {
    },
    actions: {
        login({state, commit}, authRequest) {
            commit('logging', loginState.LOGGING);
            Vue.prototype.$axios.post('/api/auth/login', authRequest).then(res => {
                localStorage.setItem('tokenResponse', JSON.stringify(res.data));
                commit('updateResponse', res.data);
            }).catch(err => {
                commit('logging', loginState.NOT_LOGGED);
            })
        },
        logout({commit}) {
            localStorage.removeItem('tokenResponse');
            commit('clearResponse');
            router.push('/auth');
        }
    }
};

export default AuthModule;

export function redirectToLogin(commit) {
    localStorage.removeItem('tokenResponse');
    commit('auth/clearResponse', {root: true});
    router.push('/auth');
}

export {loginState};
