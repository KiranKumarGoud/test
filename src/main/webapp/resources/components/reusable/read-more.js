Vue.component("read-more", {
    template: `
        <div id="read-more-div" >
            <span  v-if="typeof contentValue == 'string' &&contentValue.length > 30">
            <span>
                {{$props.contentValue.substring(0,25)}}
            </span>
            <span class="readMoreText" style="color:blue;">
            (Read More...)
            <q-popup-proxy>
                    <q-banner style="width:250px">{{$props.contentValue }}</q-banner>
                </q-popup-proxy>             
            </span>
            </span>
            <span v-else>{{$props.contentValue}} </span>
        </div>
    `,
    props: {
        titleHeader: {
            type: String,
            default: ""
        },
        contentValue: {
            type: String,
            default: ""
        }
    },
    mounted() {

    },
    data() {
        return {
           
        }
    }
});
