<template>
    <v-autocomplete
            :value="value"
            @input="$emit('input', $event)"
            hint="Choose diagnoses"
            :items="diseases"
            label="Diagnoses*"
            persistent-hint
			item-text="name"                        
            :rules="rules"
            return-object
            :loading="loading"
            :multiple="multiple"
            :chips="multiple"
    >
        <template v-slot:no-data>
            <v-list-item>
                <v-list-item-title>
                    There are no defined diagnoses
                </v-list-item-title>
            </v-list-item>
        </template>

    </v-autocomplete>
</template>

<script>

    import {mapActions, mapState} from "vuex";
    import JSOG from 'jsog';

    export default {
        name: "DiseaseSelection",
        data: () => ({
            rules: [v => !!v || 'Diagnoses is required'],
            loading: false,
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
            ...mapState('disease/disease', ['diseases']),
        },
        watch: {
            diseases(val) {
                this.loading = false;
            }
        },
        methods: {
            ...mapActions('disease/disease', ['getDiseases']),
        },
        mounted() {
            this.loading = true;
            console.log("getDiseases");
            this.getDiseases();
        }
    }
</script>

<style scoped>

</style>
