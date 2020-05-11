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
        	console.log(intervention.dateTime);
            state.intervention.push(intervention);
        },
        deleteIntervention(state, intervention) {
            let index = state.intervention.findIndex(c => c.id === intervention.id);
            state.intervention.splice(index, 1);
        },
        updateIntervention(state, intervention) {
        	console.log("mutations updateIntervention");
            state.intervention = [
                ...state.intervention.filter(c => c.id !== intervention.id),
                intervention
            ]
        }
    },
    actions: {
    	async getAllInterventionApi({rootState, commit}) {
            try {
                let res = await Vue.prototype.$axios.get('/api/intervention/',
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token} });
                console.log("actions getAllInterventionApi = " + res.data);
                res.data.forEach(item => console.log(item));
                commit('setAllIntervention', res.data);
            } catch (err) {
                defaultError(err);
            }
        },
        async getClinicInterventionApi({rootState, commit}, clinic) {
            try {
                console.log("actions getClinicInterventionApi clinic = " + clinic);
                let res = await Vue.prototype.$axios.get('/api/intervention/' + clinic,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token} });
                console.log("actions getClinicInterventionApi = " + res.data);
                res.data.forEach(item => console.log(item));
                commit('setAllIntervention', res.data);
            } catch (err) {
                defaultError(err);
            }
        },
        async addInterventionApi({rootState, commit}, intervention) {
            try {
            	console.log("addIntervention");            	
                let {data: added} = await Vue.prototype.$axios.post('/api/intervention', intervention,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token} });
                commit('addIntervention', added);
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
            console.log("updateInterventionApi");
            try {
                let {data: modified} = await Vue.prototype.$axios.put('/api/intervention', intervention, 
                		{headers: {"Authorization": 'Bearer ' + rootState.auth.token}});
                commit('updateIntervention', modified);
            } catch (err) {
                defaultError(err);
            }
        },
    },
};
