<template>
    <v-dialog
            :value="value"
            @input="$emit('input', $event)"
            max-width="600"
            @keydown.enter="$emit('close')"
    >
        <v-card>
            <v-card-title class="headline">Appointment</v-card-title>

            <v-card-text>
                Are you sure that you want to make an appointment for {{typeName}}?
                It will cost you {{typePrice}}, and the clinic where that appointment will
                be don is {{clinicName}}.
                <br>
                Are you sure that you want that to be on {{date | filterDate}}
            </v-card-text>
            <v-card-actions>
                <v-btn
                        color="green darken-1"
                        text
                        @click="$emit('close')"
                >
                    Close
                </v-btn>
                <v-spacer></v-spacer>
                <v-btn
                        color="green darken-1"
                        text
                        @click="$emit('close')"
                >
                    Sure
                </v-btn>

            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    export default {
        name: "RequestDialog",
        data: () => ({}),
        computed: {
            clinicName() {
                return this.clinic ? this.clinic.name : '';
            },
            typeName() {
                return this.type ? this.type.name : '';
            },
            typePrice() {
                return this.type ? this.type.price : '';
            }
        },
        props: {
            value: {
                type: Boolean,
                default: false
            },
            clinic: null,
            date: null,
            type: null
        },
        methods: {},
        filters:{
            filterDate(date) {
                if (!date) return 'never';
                return new Date(date).toLocaleDateString();
            }
        }
    }
</script>

<style scoped>

</style>
