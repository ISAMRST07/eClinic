import Vue from 'vue';
import {defaultError} from "../../utils/defaultErrorBehavior";

export default {
    namespaced: true,
    state: {
    	adminClinic: ''
    },
    mutations: {
    	setClinic(state, clinic) {
    		console.log("mutations setClinic = ");
    		console.log(clinic);
            Vue.set(state, 'adminClinic', clinic);
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
