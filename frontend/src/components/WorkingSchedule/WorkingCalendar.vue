<template>
    <v-row class="fill-height">
        <v-col>
            <v-sheet height="64">
                <v-toolbar color="white" flat>
                    <v-btn @click="setToday" class="mr-4" color="grey darken-2" outlined>
                        Today
                    </v-btn>
                    <v-btn @click="prev" color="grey darken-2" fab small text>
                        <v-icon small>mdi-chevron-left</v-icon>
                    </v-btn>
                    <v-btn @click="next" color="grey darken-2" fab small text>
                        <v-icon small>mdi-chevron-right</v-icon>
                    </v-btn>
                    <v-toolbar-title>{{ title }}</v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-menu bottom right>
                        <template v-slot:activator="{ on }">
                            <v-btn
                                    color="grey darken-2"
                                    outlined
                                    v-on="on"
                            >
                                <span>{{ typeToLabel[type] }}</span>
                                <v-icon right>mdi-menu-down</v-icon>
                            </v-btn>
                        </template>

                        <v-list>
                            <v-list-item @click="type = 'day'">
                                <v-list-item-title>Day</v-list-item-title>
                            </v-list-item>
                            <v-list-item @click="type = 'week'">
                                <v-list-item-title>Week</v-list-item-title>
                            </v-list-item>
                            <v-list-item @click="type = 'month'">
                                <v-list-item-title>Month</v-list-item-title>
                            </v-list-item>
                            <v-list-item @click="type = '4day'">
                                <v-list-item-title>4 days</v-list-item-title>
                            </v-list-item>
                        </v-list>
                    </v-menu>
                </v-toolbar>
            </v-sheet>
            <v-sheet height="600">
                <v-calendar
                        :event-color="getEventColor"
                        :events="events"
                        :type="type"
                        @change="updateRange"
                        @click:date="viewDay"
                        @click:event="showEvent"
                        @click:more="viewDay"
                        color="primary"
                        ref="calendar"
                        v-model="focus"
                ></v-calendar>
                <v-menu
                        :activator="selectedElement"
                        :close-on-content-click="false"
                        offset-x
                        v-model="selectedOpen"
                >
                    <v-card
                            color="grey lighten-4"
                            flat
                            min-width="350px"
                    >
                        <v-toolbar
                                :color="selectedEvent.color"
                                dark
                        >
                            <v-btn icon>
                                <v-icon>mdi-pencil</v-icon>
                            </v-btn>
                            <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
                            <v-spacer></v-spacer>
                            <v-btn icon>
                                <v-icon>mdi-heart</v-icon>
                            </v-btn>
                            <v-btn icon>
                                <v-icon>mdi-dots-vertical</v-icon>
                            </v-btn>
                        </v-toolbar>
                        <v-card-text>
                            <span v-html="selectedEvent.details"></span>
                        </v-card-text>
                        <v-card-actions>
                            <v-btn
                                    @click="selectedOpen = false"
                                    color="secondary"
                                    text
                            >
                                Cancel
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-menu>
            </v-sheet>
        </v-col>
    </v-row>
</template>


<script>
    import {mapState} from "vuex";

    export default {
        name: "WorkingCalendar",
        data: () => ({
            focus: '',
            type: 'month',
            typeToLabel: {
                month: 'Month',
                week: 'Week',
                day: 'Day',
                '4day': '4 Days',
            },
            start: null,
            end: null,
            selectedEvent: {},
            selectedElement: null,
            selectedOpen: false,
            events: [],
            colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
            names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Birthday', 'Conference', 'Party'],
        }),
        computed: {
            ...mapState('patients', ['patients']),
            ...mapState('auth', ['role', 'user']),
            title() {
                const {start, end} = this
                if (!start || !end) {
                    return ''
                }

                const startMonth = this.monthFormatter(start)
                const endMonth = this.monthFormatter(end)
                const suffixMonth = startMonth === endMonth ? '' : endMonth

                const startYear = start.year
                const endYear = end.year
                const suffixYear = startYear === endYear ? '' : endYear

                const startDay = start.day + this.nth(start.day)
                const endDay = end.day + this.nth(end.day)

                switch (this.type) {
                    case 'month':
                        return `${startMonth} ${startYear}`
                    case 'week':
                    case '4day':
                        return `${startMonth} ${startDay} ${startYear} - ${suffixMonth} ${endDay} ${suffixYear}`
                    case 'day':
                        return `${startMonth} ${startDay} ${startYear}`
                }
                return ''
            },
            monthFormatter() {
                return this.$refs.calendar.getFormatter({
                    timeZone: 'UTC', month: 'long',
                })
            },
        },
        mounted() {
            this.$refs.calendar.checkChange()
        },
        methods: {
            viewDay({date}) {
                this.focus = date
                this.type = 'day'
            },
            getEventColor(event) {
                return event.color
            },
            setToday() {
                this.focus = this.today
            },
            prev() {
                this.$refs.calendar.prev()
            },
            next() {
                this.$refs.calendar.next()
            },
            showEvent({nativeEvent, event}) {
                const open = () => {
                    this.selectedEvent = event
                    this.selectedElement = nativeEvent.target
                    setTimeout(() => this.selectedOpen = true, 10)
                }

                if (this.selectedOpen) {
                    this.selectedOpen = false
                    setTimeout(open, 10)
                } else {
                    open()
                }

                nativeEvent.stopPropagation()
            },
            updateRange({start, end}) {

                const events = []

                const min = new Date(`${start.date}T00:00:00`)
                const max = new Date(`${end.date}T23:59:59`)
                const days = (max.getTime() - min.getTime()) / 86400000
                const eventCount = this.rnd(days, days + 20)
                console.log("KALENDAR");
                console.log(start)
                //appointmentrequest
                let appoinmentReqSize = 1
                for (let i = 0; i < appoinmentReqSize; i++) {
                    //OVO SAMO ZA TESTIRANJE, KAD SE UCITAJU PODACI IZ BAZE BRISE SE
                    const allDay = this.rnd(0, 3) === 0
                    const firstTimestamp = this.rnd(min.getTime(), max.getTime())
                    const first = new Date(firstTimestamp - (firstTimestamp % 900000))
                    const secondTimestamp = this.rnd(2, allDay ? 288 : 8) * 900000
                    const second = new Date(first.getTime() + secondTimestamp)
                    let appoinmentStart = this.formatDate(first, !allDay); //Ovde dodati podatak kad pocinje (iz baze) FORMAT YYYY-MM-DD hh:mm
                    let appoinmentEnd =  this.formatDate(second, !allDay); //Ovde dodati podatak kad se zavrsava (iz baze) FORMAT YYYY-MM-DD hh:mm
                    //----------------------------------------------------------------
                    if ( !this.isBetweenTwoDates(start.date, end.date, appoinmentStart)){
                        if ( this.isBetweenTwoDates(start.date, end.date, appoinmentEnd)){
                            appoinmentStart = start.date;
                        }
                        else{
                            continue;
                        }
                    }

                    if ( !this.isBetweenTwoDates(start.date, end.date, appoinmentEnd))
                        appoinmentEnd = end.date;
                    events.push({
                        details: "appointmentrequest",
                        name: "appointmentrequest",
                        start: appoinmentStart,
                        end: appoinmentEnd,
                        color: this.colors[1],
                    })
                }
                //interventions lista intervenicja iz baze
                let interventions = 1
                for (let i = 0; i < interventions; i++) {
                    //OVO SAMO ZA TESTIRANJE, KAD SE UCITAJU PODACI IZ BAZE BRISE SE
                    const allDay = this.rnd(0, 3) === 0
                    const firstTimestamp = this.rnd(min.getTime(), max.getTime())
                    const first = new Date(firstTimestamp - (firstTimestamp % 900000))
                    const secondTimestamp = this.rnd(2, allDay ? 288 : 8) * 900000
                    const second = new Date(first.getTime() + secondTimestamp)

                    let interventionStart = this.formatDate(first, !allDay); //Ovde dodati podatak kad pocinje (iz baze) FORMAT YYYY-MM-DD hh:mm
                    let interventionEnd =  this.formatDate(second, !allDay); //Ovde dodati podatak kad se zavrsava (iz baze) FORMAT YYYY-MM-DD hh:mm
                    //----------------------------------------------------------------
                    if ( !this.isBetweenTwoDates(start.date, end.date, interventionStart)){
                        if ( this.isBetweenTwoDates(start.date, end.date, interventionEnd)){
                            interventionStart = start.date;
                        }
                        else{
                            continue;
                        }
                    }
                    if ( !this.isBetweenTwoDates(start.date, end.date, interventionEnd))
                        interventionEnd = end.date;
                    events.push({
                        details: " intervention",
                        name: " intervention",
                        start: interventionStart,
                        end: interventionEnd,
                        color: this.colors[2],
                    })
                }

                this.start = start
                this.end = end
                this.events = events
            },
            nth(d) {
                return d > 3 && d < 21
                    ? 'th'
                    : ['th', 'st', 'nd', 'rd', 'th', 'th', 'th', 'th', 'th', 'th'][d % 10]
            },
            rnd(a, b) {
                return Math.floor((b - a + 1) * Math.random()) + a
            },
            isBetweenTwoDates(fromD, toD, checkD){
                let fromDSplit = fromD.split('-')
                fromD = fromDSplit[0]+""+fromDSplit[1]+""+fromDSplit[2]+"0000";
                let toDSplit = toD.split('-')
                toD = toDSplit[0]+""+toDSplit[1]+""+toDSplit[2]+"2359";
                let checkDateTime = checkD.split(' ');
                let checkDateSplit = checkDateTime[0].split('-')
                let checkTimeSplit = checkDateTime[1].split(':')


                if(parseInt(checkDateSplit[1])<10){
                    checkDateSplit[1] = '0'+checkDateSplit[1];
                }
                if(parseInt(checkDateSplit[2])<10){
                    checkDateSplit[2] = '0'+checkDateSplit[2];
                }


                if(parseInt(checkTimeSplit[0])<10){
                    checkTimeSplit[0] = '0'+checkTimeSplit[0];
                }
                if(parseInt(checkTimeSplit[1])<10){
                    checkTimeSplit[1] = '0'+checkTimeSplit[1];
                }
                checkD = checkDateSplit[0]+""+checkDateSplit[1]+""+checkDateSplit[2]+""+checkTimeSplit[0]+""+checkTimeSplit[1];
               //  console.log(fromD)
               //  //console.log(new Date(fromD))
               //  console.log(toD)
               //  //console.log(new Date(toD))
               //  console.log(checkD)
               // console.log(parseInt(checkD) >= parseInt(fromD) && parseInt(checkD)<= parseInt(toD))
                return true;
             //   return new Date(checkD) >= new Date(fromD) && new Date(checkD) <= new Date(toD);
            },
            formatDate(a, withTime) {
                return withTime
                    ? `${a.getFullYear()}-${a.getMonth() + 1}-${a.getDate()} ${a.getHours()}:${a.getMinutes()}`
                    : `${a.getFullYear()}-${a.getMonth() + 1}-${a.getDate()} ${a.getHours()}:${a.getMinutes()}`
            },
        },
    }
</script>

<style scoped>

</style>