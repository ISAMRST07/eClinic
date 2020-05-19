<template>
    <v-autocomplete
            :value="value"
            @input="$emit('input', $event)"
            hint="Choose intervention type"
            :items="interventionType"
            label="Intervention type*"
            persistent-hint
            item-text="name"
            item-value="name"
            :rules="rules"
            return-object
            prepend-icon="mdi-hospital-building"
            :multiple="multiple"
            :chips="multiple"
    >
        <template v-slot:no-data>
            <v-list-item>
                <v-list-item-title>
                    There are no defined intervention types
                </v-list-item-title>
            </v-list-item>
        </template>

    </v-autocomplete>
</template>

<script>

    import {mapActions, mapState} from "vuex";
    import {ClinicalAdmin, ClinicalCenterAdmin} from "../../utils/DrawerItems";
    import JSOG from 'jsog';

    export default {
        name: "InterventionTypeSelection",
        data: () => ({
            rules: [v => !!v || 'Intervention type is required'],
        }),
        props: {
            value: null,
            disabled: {
                type: Boolean,
                value: false
            },
            multiple: {
                type: Boolean,
                value: false
            }
        },
        computed: {
            ...mapState('interventionType/interventionType', ['interventionType']),
            ...mapState('auth', ['user']),
            ...mapState('auth', ['clinic']),
        },
        methods: {
            ...mapActions('interventionType/interventionType', ['getAllInterventionTypeApi']),
            ...mapActions('interventionType/interventionType', ['getClinicInterventionTypeApi']),
        },
        created() {
           switch (this.user.type) {
                case ClinicalAdmin.code:
                   	console.log("user = ClinicalAdmin id = " + this.clinic.id);
                   	this.getClinicInterventionTypeApi(this.clinic.id);
                    break;
                default:
                    console.log("user = ClinicalCenterAdmin")
                    this.getAllInterventionTypeApi();
                    break;
            }
        }
    }
</script>

<style scoped>

</style>
