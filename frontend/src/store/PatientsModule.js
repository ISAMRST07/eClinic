import Vue from 'vue';
import {defaultError} from "../utils/defaultErrorBehavior";

export default {
    namespaced: true,
    state: {
        patients: [],
    },
    mutations: {
        setAllPatients(state, patients) {
            Vue.set(state, 'patients', patients);
        },
        deletePatient(state, patient) {
            let index = state.patients.findIndex(c => c.id === patient.id);
            state.patients.splice(index, 1);
        },
        updatePatient(state, patient) {
            state.patients = [
                ...state.patients.filter(c => c.id !== patient.id),
                patient
            ]
        }
    },
    actions: {
        async getPatients({rootState, commit}) {
            try {
                let res = await Vue.prototype.$axios.get('/api/patient',
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token}});
                commit('setAllPatients', res.data);
            } catch (err) {
                defaultError(err);
            }
        },
        async getPatientsByClinic({rootState, commit}, clinic) {
            try {
                let res = await Vue.prototype.$axios.get(`/api/patient/clinic/${clinic.id}`,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token}});
                commit('setAllPatients', res.data);
            } catch (err) {
                defaultError(err);
            }
        },
        async createRecord({rootState, commit}, user) {
            try {
                let res = await Vue.prototype.$axios.put(`/api/patient/create-record/${user.id}`, null,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token}});
                commit('updatePatient', res.data);
            } catch (err) {
                defaultError(err);
            }
        },
        async deletePatientApi({rootState, commit}, patient) {
            try {
                let res = await Vue.prototype.$axios.delete(`/api/patient/${patient.id}`,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token}});
                commit('deletePatient', res.data);
            } catch (err) {
                defaultError(err);
            }
        },
    },
};
