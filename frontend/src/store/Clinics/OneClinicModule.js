import Vue from 'vue';
import {defaultError} from "../../utils/defaultErrorBehavior";
import {emptyClinic} from "../../utils/skeletons";

export default {
    namespaced: true,
    state: {
        clinic: emptyClinic,
    },
    mutations: {
        getOneClinic(state, clinic){
        	console.log("mutations getOneClinic = " + clinic);
        	console.log(clinic);
            Vue.set(state, 'clinic', clinic);
        }
    },
    actions: {
        async getOneClinicApi({rootState, commit}, clinicId) {
            try {
                console.log("getOneClinicApi");
                let res = await Vue.prototype.$axios.get('/api/clinic/'+clinicId,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token} });
                console.log("actions getone clinic = " + res.data);
                console.log(res.data);
                commit('getOneClinic', res.data);
            } catch (err) {
                defaultError(err);
            }
        }
    },
};
