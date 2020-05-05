import Vue from 'vue'

const tokenResponse = JSON.parse(localStorage.getItem('tokenResponse'));
let initialExpiresIn = 0;
let initialToken = '';
let initialUser = null;
if (tokenResponse) {
    initialExpiresIn = tokenResponse.expiresIn;
    if (initialExpiresIn > 0) {
        initialToken = tokenResponse.accessToken;
        initialUser = tokenResponse.loggedUser;
    }
}


const AuthModule = {
    namespaced: true,
    state: {
        token: initialToken,
        expiresIn: initialExpiresIn,
        user: initialUser
    },
    mutations: {
        updateResponse(state, tokenResponse) {
            state.token = tokenResponse.accessToken;
            state.expiresIn = tokenResponse.expiresIn;
            state.user = tokenResponse.loggedUser;
        }
    },
    getters: {
        loggedIn: state => !!state.user
    },
    actions: {
        login({state, commit}, authRequest) {
            Vue.prototype.$axios.post('/api/auth/login', authRequest).then(res => {
                commit('updateResponse', res.data);
            }).catch(err => {
                console.error(err);
            })
        }
    }
};

export default AuthModule;
