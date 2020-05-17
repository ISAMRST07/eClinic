import Vue from 'vue';
import {defaultError} from "../../utils/defaultErrorBehavior";
import JSOG from 'jsog'

export default {
    namespaced: true,
    state: {
        vacationRequest: [],
    },
    mutations: {
        setAllVacationRequest(state, vacationRequest) {
            console.log("mutations getall vacationRequest = " + vacationRequest);
            Vue.set(state, 'vacationRequest', JSOG.decode(vacationRequest));
        },
        addVacationRequest(state, vacationRequest) {
            console.log("mutations vacationRequest");
            state.vacationRequest.push(vacationRequest);
        },
        deleteVacationRequest(state, vacationRequest) {
            let index = state.vacationRequest.findIndex(c => c.id === vacationRequest.id);
            state.vacationRequest.splice(index, 1);
        },
        updateVacationRequest(state, vacationRequest) {
            console.log("updateVacationRequest");
            state.vacationRequest = [
                ...state.vacationRequest.filter(c => c.id !== vacationRequest.id),
                vacationRequest
            ]
        }
    },
    actions: {
        async getClinicVacationRequestApi({rootState, commit}, clinicId) {
            try {
                console.log("getClinicVacationRequestApi");
                let res = await Vue.prototype.$axios.get('/api/vacationRequest/'+clinicId,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token}});
                console.log("actions getall vacationRequest = " + res.data);
                res.data.forEach(item => console.log(item));
                commit('setAllVacationRequest', res.data);
            } catch (err) {
                defaultError(err);
            }
        },
        async addVacationRequestApi({rootState, commit}, vacationRequest) {
            try {
                console.log("addVacationRequestApi");
                console.log(vacationRequest);
                let {data: added} = await Vue.prototype.$axios.post('/api/vacationRequest', vacationRequest,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token}});
                commit('addVacationRequest', added);
            } catch (err) {
                defaultError(err);
            }
        },
        async deleteVacationRequestApi({rootState, commit}, vacationRequest) {
            try {
                console.log("delete vacationRequest = ");
                console.log(vacationRequest.id);
                let res = await Vue.prototype.$axios.delete(`/api/vacationRequest/${vacationRequest.id}`,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token}});
                commit('deleteVacationRequest', vacationRequest);
            } catch (err) {
                defaultError(err);
            }
        },
        async updateVacationRequestApi({rootState, commit}, vacationRequest) {
            console.log("updateVacationRequestApi");
            try {
                let {data: modified} = await Vue.prototype.$axios.put('/api/vacationRequest', vacationRequest, 
                		{headers: {"Authorization": 'Bearer ' + rootState.auth.token}});
                commit('updateVacationRequest', modified);
            } catch (err) {
                defaultError(err);
            }
        },
    },
};
