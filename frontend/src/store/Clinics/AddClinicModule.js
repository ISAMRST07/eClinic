import {emptyClinic} from "../../utils/skeletons";
import Vue from 'vue';

export default {
    namespaced: true,
    state: {
        clinic: emptyClinic
    },
    mutations: {
        updateClinic(state, newParts) {
            state.clinic = Object.assign({}, state.clinic, newParts);
        }
    },
    actions: {
        async saveClinic({commit}, clinic) {
            try {
                let {data: res} = await Vue.prototype.$axios.post('/api/clinic', clinic);
                // tu ce ic update za prikaz
                commit('clinics/readClinics/addClinic', res, {root: true});
            } catch (err) {
                console.log(err);
                // tu moze ic neki toast
            }
        }
    },
};
