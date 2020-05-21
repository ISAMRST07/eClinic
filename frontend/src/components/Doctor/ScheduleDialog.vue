<template>
    <v-dialog
            :value="value"
            @input="$emit('input', $event)"
            max-width="600"
            @keydown.enter="$emit('close')"
    >
        <v-card>
            <v-card-title class="headline">Appointment</v-card-title>
            <v-form ref="form" @submit.prevent="accept">
                <v-card-text class="subtitle-1">
                    <div>The appointment will be done for {{typeName}}</div>
                    <div>It will cost you ${{typePrice}}.</div>
                    <div>The date of the appointment is {{date | filterDate}}.</div>
                    <div>The doctor you chose is {{ doctorName }}!</div>
                    <div class="text--primary text--darken-2">Now, you should choose your preferred time of appointment.</div>
                    <v-menu
                            ref="menu"
                            v-model="menu"
                            :close-on-content-click="false"
                            :nudge-right="40"
                            :return-value.sync="time"
                            transition="scale-transition"
                            offset-y
                            max-width="290px"
                            min-width="290px"
                    >
                        <template v-slot:activator="{ on }">
                            <v-text-field
                                    v-model="time"
                                    label="Choose appointment time"
                                    prepend-icon="mdi-clock"
                                    readonly
                                    v-on="on"
                                    :error="error.isError"
                                    :error-messages="error.errorMessages"
                            ></v-text-field>
                        </template>
                        <v-time-picker
                                v-if="menu"
                                v-model="time"
                                full-width
                                format="24h"
                                @click:minute="$refs.menu.save(time)"
                        ></v-time-picker>
                    </v-menu>
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
                            @click="accept"
                    >
                        Sure
                    </v-btn>

                </v-card-actions>
            </v-form>
        </v-card>
    </v-dialog>
</template>

<script>
    import {DayOfTheWeek} from "../../utils/DayOfTheWeek";
    import {mapActions} from "vuex";

    export default {
        name: "ScheduleDialog",
        data: () => ({
            time: '',
            menu: false,
            error: {
                isError: false,
                errorMessages: ''
            }
        }),
        computed: {
            typeName() {
                return this.type ? this.type.name : '';
            },
            typePrice() {
                return this.type ? this.type.price : '';
            },
            doctorName() {
                return this.doctor ? this.doctor.name : '';
            }
        },
        props: {
            value: {
                type: Boolean,
                default: false
            },
            clinic: null,
            date: null,
            type: null,
            doctor: null
        },
        methods: {
            ...mapActions('appointmentRequests', ['addRequestApi']),
            accept() {
                if(!this.validateTime()) return;
                let date = new Date(this.date);
                let parsedTime = this.parseShortTime(this.time);
                date.setHours(parsedTime.hours, parsedTime.minutes);
                this.addRequestApi({
                    dateTime: date,
                    interventionTypeID: this.type.id,
                    clinicID: this.clinic.id,
                    doctorID: this.doctor.id
                });
                this.$router.push('/clinics');
            },
            validateTime() {

                this.error.isError = false;
                this.error.errorMessages = '';
                if(!this.time) {
                    this.error.isError = true;
                    this.error.errorMessages = 'Time is required.';
                    return false;
                }
                let date = new Date(this.date);
                let selectedTime = this.parseShortTime(this.time);
                let selectedDateTime = new Date(date);
                selectedDateTime.setHours(selectedTime.hours, selectedTime.minutes);

                let weekday = DayOfTheWeek[date.getDay()];
                let timeperiod = this.doctor.workingSchedule[weekday];
                let start = this.parseTime(timeperiod.start);
                let startDateTime = new Date(date);
                startDateTime.setHours(start.hours, start.minutes, start.seconds);
                if(selectedDateTime < startDateTime){
                    this.error.isError = true;
                    this.error.errorMessages = 'This is before the doctor starts workday.';
                    return false;
                }
                let end = this.parseTime(timeperiod.end);
                let endDateTime = new Date(date);
                endDateTime.setHours(end.hours, end.minutes, end.seconds);
                if(selectedDateTime > endDateTime){
                    this.error.isError = true;
                    this.error.errorMessages = 'This is after the doctor ends workday.';
                    return false;
                }
                for(timeperiod of this.doctor.busyTimes) {
                    let start = this.parseTime(timeperiod.start);
                    let startDateTime = new Date(date);
                    startDateTime.setHours(start.hours, start.minutes, start.seconds);
                    let end = this.parseTime(timeperiod.end);
                    let endDateTime = new Date(date);
                    endDateTime.setHours(end.hours, end.minutes, end.seconds);

                    if(selectedDateTime => startDateTime && selectedDateTime <= endDateTime){
                        this.error.isError = true;
                        this.error.errorMessages = 'Doctor has an appointment during this time.';
                        return false;
                    }
                }
                return true;
            },
            parseTime(time) {
                let hours = time.slice(0, 2);
                let minutes = time.slice(3, 5);
                let seconds = time.slice(6, 8);
                return {
                    hours: hours,
                    minutes: minutes,
                    seconds: seconds
                };
            },
            parseShortTime(time) {
                let hours = time.slice(0, 2);
                let minutes = time.slice(3, 5);
                return {
                    hours: hours,
                    minutes: minutes,
                };
            },

        },
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
