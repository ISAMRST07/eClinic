import {emptyClinicAdmin} from "../../utils/skeletons";
import Vue from 'vue';

export default {
    namespaced: true,
    state: {
        clinicAdmins: [],
        clinicAdminToUpdate : emptyClinicAdmin
    },
    mutations: {
        setAllClinicAdmins(state, clinicAdmins){
            Vue.set(state, 'clinicAdmins', clinicAdmins);
        },
        addClinicAdmin(state, clinicAdmin) {
            state.clinicAdmins.push(clinicAdmin);
        },
        updateClinicAdmin(state, clinic) {
            state.clinicAdmins = [
                ...state.clinicAdmins.filter(c => c.id !== clinic.id),
                clinic
            ]
        },

        deleteClinicAdmins(state, clinicAdmin) {
            let index = state.clinicAdmins.findIndex(c => c.id === clinicAdmin.id);
            state.clinicAdmins.splice(index, 1);
        },
    },
    actions: {
        async saveClinicAdmin({commit}, clinicAdmin) {
            try {
                let {data: res} = await Vue.prototype.$axios.post('/api/clinicadministrator', clinicAdmin);
                // tu ce ic update za prikaz
                commit('addClinicAdmin', res);
            } catch (err) {
                console.error(err);
                // tu moze ic neki toast
            }
        },
        async getClinicAdmins({commit}) {
            try {
                let res = await Vue.prototype.$axios.get('/api/clinicadministrator/getAll');
                console.log(res.data);
                commit('setAllClinicAdmins', res.data);
            } catch (err) {
                console.error(err);
            }
        },
        async modifyClinicApi({commit}, clinic) {
            try {
                let {data: res} = await Vue.prototype.$axios.put(`/api/clinicadministrator/${clinic.id}`, clinic);
                commit('updateClinicAdmin', res);
            } catch (err) {
                console.error(err);
            }
        },
        async deleteClinicAdminApi({commit}, clinicAdministrator) {
            try{
                let res = await Vue.prototype.$axios.delete(`/api/clinicadministrator/${clinicAdministrator.id}`);
                commit('deleteClinicAdmins', clinicAdministrator);
            } catch(err) {
                console.error(err);
            }
        }

    },
};
