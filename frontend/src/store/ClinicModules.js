import {emptyClinic} from "../utils/skeletons";
import Vue from 'vue'
const AddClinicModule = {
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
                console.log(commit);
                console.log(clinic);
                let {data: res} = await Vue.prototype.$axios.post('/api/clinic', clinic);
                // tu ce ic update za prikaz
                console.log(res);
            } catch (err) {
                console.log(err);
                // tu moze ic neki toast
            }
        }
    },
};

const ClinicModules = {
    namespaced: true,
    modules: {
        addClinic: AddClinicModule
    }
};

export default ClinicModules;
