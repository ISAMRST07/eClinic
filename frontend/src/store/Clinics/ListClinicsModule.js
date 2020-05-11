import Vue from 'vue';
import {defaultError} from "../../utils/defaultErrorBehavior";

export default {
    namespaced: true,
    state: {
        clinics: []
    },
    mutations: {
        setAllClinics(state, clinics) {
            Vue.set(state, 'clinics', clinics);
        },
        addClinic(state, clinic) {
            state.clinics.push(clinic);
        },
        deleteClinic(state, clinic) {
            let index = state.clinics.findIndex(c => c.id === clinic.id);
            state.clinics.splice(index, 1);
        },
        updateClinic(state, clinic) {
            state.clinics = [
                ...state.clinics.filter(c => c.id !== clinic.id),
                clinic
            ]
        }
    },
    getters: {
        clinicById: state => id => {
            return state.clinics.find(clinic => clinic.id === id);
        }
    },
    actions: {
        async getClinics({rootState, commit}) {
            try {
                let res = await Vue.prototype.$axios.get('/api/clinic/',
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token}});
                commit('setAllClinics', res.data);
            } catch (err) {
                defaultError(err);
            }
        },
        async deleteClinicApi({rootState, commit}, clinic) {
            try {
                let res = await Vue.prototype.$axios.delete(`/api/clinic/${clinic.id}`,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token}});
                commit('deleteClinic', clinic);
            } catch (err) {
                defaultError(err);
            }
        },

    },
};
