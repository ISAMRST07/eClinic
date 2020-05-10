import Vue from 'vue';
import {defaultError} from "../../utils/defaultErrorBehavior";
import JSOG from 'jsog'
export default {
    namespaced: true,
    state: {
        doctor: [],
    },
    mutations: {
        setAllDoctor(state, doctor){
        	console.log("mutations getall doctor = " + doctor);
            Vue.set(state, 'doctor', JSOG.decode(doctor));
        },
        addDoctor(state, doctor) {
        	console.log("mutations addDoctor");
        	console.log(doctor.name);
        	
            state.doctor.push(doctor);
        },
        deleteDoctor(state, doctor) {
            let index = state.doctor.findIndex(c => c.id === doctor.id);
            state.doctor.splice(index, 1);
        },
        updateDoctor(state, doctor) {
        	console.log("updateDoctor");
            state.doctor = [
                ...state.doctor.filter(c => c.id !== doctor.id),
                doctor
            ]
        }
    },
    actions: {
        async getDoctor({rootState, commit}) {
            try {
                let res = await Vue.prototype.$axios.get('/api/doctor/getAll',
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token} });
                console.log("actions getall doctor = " + res.data);
                res.data.forEach(item => console.log(item));
                commit('setAllDoctor', res.data);
            } catch (err) {
                defaultError(err);
            }
        },
        async getClinicDoctor({rootState, commit}, clinicId) {
            try {
            	console.log("getClinicDoctor = " + clinicId);
                let res = await Vue.prototype.$axios.get('/api/doctor/getDoctorForClinic/'+clinicId,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token} });
                console.log("actions getall doctor = " + res.data);
                res.data.forEach(item => console.log(item));
                commit('setAllDoctor', res.data);
            } catch (err) {
                defaultError(err);
            }
        },
        async addDoctorApi({rootState, commit}, doctor) {
            try {
            	console.log("adddoctorapi");            	
                let {data: added} = await Vue.prototype.$axios.post('/api/doctor', doctor,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token} });
                commit('addDoctor', added);
            } catch (err) {
                defaultError(err);
            }
        },
        async deleteDoctorApi({rootState, commit}, doctor) {
            try {
            	console.log("delete doctor = ");
            	console.log(doctor.id);
                let res = await Vue.prototype.$axios.delete(`/api/doctor/deleteDoctor/${doctor.id}`,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token} });
                commit('deleteDoctor', doctor);
            } catch (err) {
                defaultError(err);
            }
        },
        async updateDoctorApi({rootState,commit}, doctor) {
            console.log("updateDoctorApi");
            try {
                let {data: modified} = await Vue.prototype.$axios.put('/api/doctor', doctor, {headers: {"Authorization": 'Bearer ' + rootState.auth.token}});
                commit('updateDoctor', modified);
            } catch (err) {
                defaultError(err);
            }
        },
    },
};
