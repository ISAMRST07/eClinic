import {emptyClinicAdmin} from "../../utils/skeletons";
import Vue from 'vue';
import {defaultError} from "../../utils/defaultErrorBehavior";

export default {
    namespaced: true,
    state: {
        unregisteredUsers: [],
    },
    mutations: {
        setAllUnregisteredUsers(state, unregisteredUsers){
            Vue.set(state, 'unregisteredUsers', unregisteredUsers);
        }
    },
    actions: {
        async getUnregisteredUsers({rootState, commit}) {
            try {
                let res = await Vue.prototype.$axios.get('/api/unregisteredusers',
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token} });
                console.log(res.data);
                commit('setAllUnregisteredUsers', res.data);
            } catch (err) {
                console.log(commit);
                defaultError(err, commit);
            }
        },
        async sendEmailApi({rootState, dispatch}, unregisteredUser) {
            try{
                let res = await Vue.prototype.$axios.put(`/api/unregisteredusers/${unregisteredUser.id}`,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token} });
                dispatch('getUnregisteredUsers');
            } catch(err) {
                defaultError(err);
            }
        }

    },
};
