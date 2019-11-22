Vue.component("multi-select", {
  template: `<div class="multi-select">
    <q-select
      dense
      @focus="triggerFilter"
      options-dense
      emit-value
      outlined
      multiple
      ref="multiselect"
      :disable="$props.disable"
      :readonly="$props.readonly"
      v-model="propsSelectValue"
      :use-input="propsSelectValue.length == 0 ? true : false"
      fill-input
      :placeholder="propsSelectValue.length == 0 ? $props.placeholder : ''"
      input-debounce="0"
      :options="$props.optionsList"
      >
      <template v-slot:no-option>
        <q-item>
          <q-item-section class="text-grey">
            No results
          </q-item-section>
        </q-item>
      </template>
    </q-select>
  </div>`,
  props: {
    disable: {
      type: Boolean
    },
    readonly: {
      type:Boolean
    },
    placeholder: {
      type: String
    },
    optionsList: {
      type: Array,
      default: [],
      required: true
    },
    selectValue: {
      required: true,
      default: [],
      type: Array
    }
  },
  methods: {
    triggerFilter() {
      this.$emit('current-change-event', this.$props.selectValue)
    }
  },
  computed: {
    propsSelectValue: {
      get(){
        return this.$props.selectValue
      },
      set(value){
        this.$emit('change-event', value)
      }
    } 
  }
})