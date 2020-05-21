<template>
    <v-dialog
            :value="value"
            @input="$emit('input', $event)"
            @keydown.enter="$emit('close')"
            max-width="700"
            scrollable
    >
        <v-card>
            <v-card-title class="headline">Availability</v-card-title>

            <v-calendar
                    color="primary"
                    type="day"
                    :value="date"
                    :events="events"
            >

                <template v-slot:day-body="{ time }">
                    <template>

                    </template>
                </template>
            </v-calendar>

            <v-card-actions>
                <v-spacer></v-spacer>

                <v-btn
                        ref="btCloseDelete"
                        color="green darken-1"
                        text
                        @click="$emit('close')"
                >
                    Close
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>

    import {DayOfTheWeek} from "../../utils/DayOfTheWeek";

    export default {
        name: "DialogAvailable",
        props: {
            value: {
                type: Boolean,
                default: false,
            },
            doctor: null,
            date: null,
        },
        computed: {
            deleteName() {
                return this.doctor ? this.doctor.name : '';
            },
            events() {
                if (!this.doctor) return [];

                let events = [];
                let date = new Date(this.date);
                let weekday = DayOfTheWeek[date.getDay()];
                let timeperiod = this.doctor.workingSchedule[weekday];
                let start = this.parseTime(timeperiod.start);
                let startDateTime = new Date(date);
                startDateTime.setHours(start.hours, start.minutes, start.seconds)
                let end = this.parseTime(timeperiod.end);
                let endDateTime = new Date(date);
                endDateTime.setHours(end.hours, end.minutes, end.seconds);
                events.push({
                    start: this.formatDate(startDateTime),
                    name: 'Workday starts',
                    color: 'gray'
                });
                events.push({
                    start: this.formatDate(endDateTime),
                    name: 'Workday ends',
                    color: 'gray'
                });
                for(timeperiod of this.doctor.busyTimes) {
                    let start = this.parseTime(timeperiod.start);
                    let startDateTime = new Date(date);
                    startDateTime.setHours(start.hours, start.minutes, start.seconds)
                    let end = this.parseTime(timeperiod.end);
                    let endDateTime = new Date(date);
                    endDateTime.setHours(end.hours, end.minutes, end.seconds);
                    events.push({
                        start: this.formatDate(startDateTime),
                        end: this.formatDate(endDateTime),
                        name: 'Unavailable',
                        color: 'primary'
                    });
                }
                return events;
            }
        },
        methods: {
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
            formatDate(a) {
                return `${a.getFullYear()}-${a.getMonth() + 1}-${a.getDate()} ${a.getHours()}:${a.getMinutes()}`;
            }
        },
        filters: {
            filterHours(hour) {
                // if(hour == null) return;
                // alert(hour);
                // let ampm = hour.slice(-2);
                // if (ampm === 'AM') return hour.slice(0, -2);
                // else {
                //     let hhmmpm = hour.slice(0, -2);
                //     let hh = hhmmpm.slice(0, 2);
                //     let mm = hhmmpm.slice(3, 5);
                //     return `${hh + 12}:${mm}`;
                // }
            }
        }
    }
</script>

<style scoped>

</style>
