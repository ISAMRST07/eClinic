import Vue from 'vue';
import {defaultError} from "../../utils/defaultErrorBehavior";
import {emptyClinic} from "../../utils/skeletons";

export default {
    namespaced: true,
    state: {
    	adminClinic: emptyClinic
    },
    mutations: {
    	setClinic(state, clinic) {
            Vue.set(state, 'adminClinic', clinic);
        },
        updateClinic(state, newParts) {
            state.adminClinic = Object.assign({}, state.adminClinic, newParts);
        }
    },
    actions: {
        async getAdminClinicApi({rootState, commit}, clinicId) {
            try {
            	console.log("getAdminClinicApi clinicId = " + clinicId);
                let res = await Vue.prototype.$axios.get('/api/clinic/' + clinicId,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token}});
                console.log("res.data = ");
                console.log(res.data);
                commit('setClinic', res.data);
            } catch (err) {
                defaultError(err);
            }
        },

    },
};
