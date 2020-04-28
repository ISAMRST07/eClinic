import Vue from 'vue';

export default {
    namespaced: true,
    state: {
        clinics: []
    },
    mutations: {
        setAllClinics(state, clinics){
            Vue.set(state, 'clinics', clinics);
        },
        addClinic(state, clinic) {
            state.clinics.push(clinic);
        }
    },
    actions: {
        async getClinics({commit}) {
            try {
                let res = await Vue.prototype.$axios.get('/api/clinic/getAll');
                commit('setAllClinics', res.data);
            } catch(err) {
                console.error(err);
            }
        }
    },
};
