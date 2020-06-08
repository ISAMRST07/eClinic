import Vue from 'vue';
import {defaultError} from "../../utils/defaultErrorBehavior";
import JSOG from 'jsog'

export default {
    namespaced: true,
    state: {
        intervention: [],
    },
    mutations: {
        setAllIntervention(state, intervention){
        	console.log("mutations setAllIntervention intervention = " + intervention);
            Vue.set(state, 'intervention', JSOG.decode(intervention));
        },
        addIntervention(state, intervention) {
        	console.log("mutations addIntervention");
        	intervention = JSOG.decode(intervention);
        	console.log(intervention);
        	state.intervention.push(intervention);
        },
        deleteIntervention(state, intervention) {
        	intervention = JSOG.decode(intervention);
            let index = state.intervention.findIndex(c => c.id === intervention.id);
            state.intervention.splice(index, 1);
        },
        updateIntervention(state, intervention) {
        	console.log("mutations updateIntervention");
        	console.log(intervention);
        	intervention = JSOG.decode(intervention);
            state.intervention = [
                ...state.intervention.filter(c => c.id !== intervention.id),
                intervention
            ]
        }
    },
    actions: {

        async getClinicInterventionApi({rootState, commit}, clinic) {
            try {
                console.log("actions getClinicInterventionApi clinic = " + clinic);
                let res = await Vue.prototype.$axios.get('/api/intervention/clinic/' + clinic,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token} });
                console.log("actions getClinicInterventionApi = " + res.data);
                res.data.forEach(item => console.log(item));
                commit('setAllIntervention', res.data);
            } catch (err) {
                defaultError(err);
            }
        },
        async getPatientInterventionApi({rootState, commit}, patientId) {
            try {
                console.log("actions getPatientInterventionApi patientId = " + patientId);
                let res = await Vue.prototype.$axios.get('/api/intervention/patient/' + patientId,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token} });
                console.log("actions getPatientInterventionApi = " + res.data);
                res.data.forEach(item => console.log(item));
                commit('setAllIntervention', res.data);
            } catch (err) {
                defaultError(err);
            }
        },
        async deleteInterventionApi({rootState, commit}, intervention) {
            try {
            	console.log("delete intervention = ");
            	console.log(intervention.id);
                let res = await Vue.prototype.$axios.delete(`/api/intervention/${intervention.id}`,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token} });
                commit('deleteIntervention', intervention);
            } catch (err) {
                defaultError(err);
            }
        },
        async updateInterventionApi({rootState,commit}, intervention) {
            try {
                console.log("updateInterventionApi");
            	let {data: modified} = await Vue.prototype.$axios.put('/api/intervention', intervention,
                		{headers: {"Authorization": 'Bearer ' + rootState.auth.token}});
                commit('updateIntervention', modified);
            } catch (err) {
                defaultError(err);
            }
        },
        async addRequestedIntervention({rootState, commit}, payload) {
            try {
                let {requestID, clinicRoomID} = payload;
                let {data: added} = await Vue.prototype.$axios.post(`/api/intervention/approve/${requestID}/${clinicRoomID}`,
                    null, {headers: {"Authorization": 'Bearer ' + rootState.auth.token}});
                commit('addIntervention', added);
            } catch (e) {
                defaultError(e);
            }
        },
        async scheduleOneClickIntervention({rootState, commit}, oneClick) {
            try {
                let {id} = oneClick;
                let userID = rootState.auth.user.id;
                let {data: added} = await Vue.prototype.$axios.post(`/api/intervention/one-click/${id}/${userID}`,
                    null, {headers: {"Authorization": 'Bearer ' + rootState.auth.token}});
                commit('addIntervention', added);
            } catch (e) {
                defaultError(e);
            }
        },
        async rateClinicInterventionApi({rootState, commit}, payload) {
            try {
                let {clinicId, clinicRating, doctorId, doctorRating} = payload;
                console.log("rateClinicInterventionApi");
                console.log(payload);
                let {data: added} = await Vue.prototype.$axios.post(`/api/intervention/rate/${clinicId}/${clinicRating}/${doctorId}/${doctorRating}`,
                    null, {headers: {"Authorization": 'Bearer ' + rootState.auth.token}});
                //commit('addIntervention', added);
            } catch (e) {
                defaultError(e);
            }
        },
    },
};
