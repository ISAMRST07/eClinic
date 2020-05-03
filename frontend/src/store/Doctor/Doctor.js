import Vue from 'vue';

export default {
    namespaced: true,
    state: {
        doctor: [],
    },
    mutations: {
        setAllDoctor(state, doctor){
        	console.log("mutations getall doctor = " + doctor);
            Vue.set(state, 'doctor', doctor);
        },
        addDoctor(state, doctor) {
            state.doctor.push(doctor);
        },
        deleteDoctor(state, doctor) {
            let index = state.doctor.findIndex(c => c.id === doctor.id);
            state.doctor.splice(index, 1);
        },
        updateDoctor(state, doctor) {
            state.doctor = [
                ...state.doctor.filter(c => c.id !== doctor.id),
                doctor
            ]
        }
    },
    actions: {
        async getDoctor({commit}) {
            try {
                let res = await Vue.prototype.$axios.get('/api/doctor/getAll');
                console.log("actions getall doctor = " + res.data);
                console.log(res.data);
                commit('setAllDoctor', res.data);
            } catch (err) {
                console.error(err);
            }
        },
        async addDoctorApi({commit}, doctor) {
            try {
                let {data: added} = await Vue.prototype.$axios.post('/api/doctor', doctor);
                console.log(doctor.name);
                commit('addDoctor', added);
            } catch (err) {
                console.error(err);
            }
        },
        async deleteDoctorApi({commit}, doctor) {
            try {
            	console.log("delete doctor = ");
            	console.log(doctor.id);
                let res = await Vue.prototype.$axios.delete(`/api/doctor/deleteDoctor/${doctor.id}`);
                commit('deleteDoctor', doctor);
            } catch (err) {
                console.error(err);
            }
        }
    },
};
