Vue.component("auto-complete", {
  template: `<div class="auto-complete">
    <q-select
      dense
      class="shadow-efct-stl"
      ref="autocomplete"
      outlined
      multiple
      :disable="disableAutoComplete"
      :popup-content-style="{'width': getMaxPopupWidth}"
      popup-content-class="auto-complete-maxheight"
      placeholder="Enter Keyword"
      hide-dropdown-icon
      :options="options"
      use-input
      use-chips
      :hide-selected="false"
      @remove="removeChip($event)"
      input-debounce="0"
      v-model="gb__selections.model"
      @filter="filterFn"
    >
      <template v-slot:append>
        <q-icon
          v-if="gb__selections.model.length > 0"
          name="fa fa-angle-double-down"
          class="cursor-pointer"
          @click="showAutoCompleteSelectedDetails"
          size="18px"
        ></q-icon>
      </template>

      <template v-slot:option="scope">
        <li
          class="bg-color-option"
          v-bind="scope.itemProps"
          @mouseup="mouseUpEvent($event)"
          @mousedown="mouseDownEvent($event)"
          @keydown="keyDownEvent($event)"
          @dragstart="dragStartEvent($event)"
          @dragend="dragEndEvent($event)"
          @dragover="dragOverEvent($event)"
          @keyup="ctrlKeyUpEvent($event)"
          data-draggable="item"
          aria-grabbed="false"
          draggable="true"
          tabindex="1"
          :id="scope.opt.value"
          :data-id="scope.opt.value"
          :data-stdnameId="scope.opt.stdnameId"
          :data-value="JSON.stringify(scope.opt)"
        > 
        {{ scope.opt.label }}
        </li>
        <template v-for="item in scope.opt.sub_names" >
         <div :key="item.label" :class="'display-subnames ' + item.stdnameId"  >&nbsp;&nbsp;
         <i class="fa fa-caret-right sub-names-icon"/>
        <div  @click="applyHighlighting($event,'sub_name_' + item.value, item.stdnameId)" :id="'sub_name_' + item.value" style="position:relative;left:7px;display:inline-block;width: 94%;"   v-html="item.label.replace(new RegExp(curUserSelection, 'i'), '<b class=highlight-selection>' + item.label.match(new RegExp(curUserSelection, 'i')) + '</b>')"></div>
        </div>
        </template>
      </template>

      <template v-slot:selected-item="scope">
        <q-chip
          @click.native="chipClicked($event)"
          removable
          @remove="chipRemoveEvent(scope)"
          :tabindex="scope.tabindex"
        >
          <!-- truncate label if characters length is greater than 10 start -->
          <div>
            <div v-if="scope.opt.label.length > truncateThreshold">
              {{ scope.opt.label.substring(0,truncateThreshold) }}
              <span>
                ...
                <q-tooltip>{{ scope.opt.label }}</q-tooltip>
              </span>
            </div>
            <div v-else>{{ scope.opt.label }}</div>
          </div>
          <!-- truncate logic end -->
          <!-- show operator selection start-->
          <div v-if="!$props.hideOperators" class="cursor-pointer text-weight-bolder" style="width: 35px">
            <div class="row inline rotate-90 show-dots">
              <template v-if="$props.dontShowOperators.indexOf($props.selectedValue) == -1" v-for="oper in operators">
                <q-btn
                  v-if="removeNotCondition(oper.value, scope)"
                  :key="oper.value"
                  :color="scope.opt.operator == oper.value ? 'green-10': 'grey'"
                  flat
                  @click="changeOperator(scope, oper.value)"
                  icon="fa fa-dot-circle cursor-pointer"
                  size="5px"
                  style="padding: 1px"
                >
                  <q-tooltip
                    anchor="center right"
                    self="center left"
                    :offset="[10, 10]"
                  >{{oper.label}}</q-tooltip>
                </q-btn>
              </template>
            </div>
          </div>
          <!-- show operator selection end-->
        </q-chip>
      </template>

      <template v-slot:no-option>
        <q-item>
          <q-item-section>
            <div class="text-capitalize text-center q-pa-xl">no records found</div>
            <q-btn label="report" @click="reportClicked"></q-btn>
          </q-item-section>
        </q-item>
      </template>

    </q-select>
    <div>
      <q-dialog v-model="detailsDialog" persistent >
        <q-card style="overflow:hidden;">
          <q-card-section class="bg-secondary">
            <div class="text-white text-h6">
              Selected Values
              <q-btn
                icon="fa fa-times"
                class="text-white closeButton"
                flat
                @click="detailsDialog = false"
              ></q-btn>
            </div>
            <div></div>
          </q-card-section>

          <q-separator></q-separator>

          <q-card-section style="max-height: 50vh; min-width:380px;" class="scroll">
            <div>
              <div class="dialogHeadings" v-if="andList.length > 0"><strong>Include</strong></div>
              <div class="winDialogCon" v-for="item in andList" :key="item.targetId">{{item.label}}</div>
            </div>
            <div>
              <div class="dialogHeadings" v-if="notList.length > 0"><strong>Exclude</strong></div>
              <div class="winDialogCon" v-for="item in notList" :key="item.targetId">{{item.label}}</div>
            </div>
            <div>
            <div class="dialogHeadings" v-if="orList.length > 0"><strong>Option</strong></div>
            <div class="winDialogCon" v-for="item in orList" :key="item.targetId">{{item.label}}</div>
          </div>
          </q-card-section>

          <q-separator ></q-separator>
          
        </q-card>
      </q-dialog>
    </div>
    <div>
      <q-dialog v-model="showReportPopup">
        <q-card>
          <q-card-section class="bg-secondary">
            <div class="text-h6 text-white">
              Report Management
              <q-btn
                icon="fa fa-times"
                class="text-white closeButton"
                flat
                @click="showReportPopup = false"
              ></q-btn>
            </div>
 
          </q-card-section>

          <q-separator ></q-separator>

          <q-card-section style="max-height: 50vh; min-width:560px" class="scroll">
            <div>
              <span class="reportLabels">Username:</span>{{userName}}
            </div>
            <div>
              <span class="reportLabels">Email:</span>test@abc.com
            </div>
            <div>
              <span class="reportLabels">Search Term:</span>
              {{autoCompleteUserEnteredValue}}
            </div>
            <div>
              <span class="reportLabels">Field:</span>
              {{$props.selectedValue}}
            </div>
          </q-card-section>

          <q-separator ></q-separator>

          <q-card-actions align="right">
            <q-btn flat label="Cancel" color="negative" v-close-popup ></q-btn>
            <q-btn flat label="Submit" color="primary" ></q-btn>
          </q-card-actions>
        </q-card>
      </q-dialog>
    </div>
  </div>`,
  props: {
    dontShowOperators: {
      type: Array,
      default: () => []
    },
    triggerApiAt: {
      type: Number,
      default: 3
    },
    showOptionsOnFocus: {
      type: Boolean,
      default: false
    },
    hideOperators: {
      type: Boolean,
      default: false
    },
    selectedValue: {
      type: [Array, String],
      default: ""
    },
    defaultSelectedOptions: {
      type: Array
    },
    searchType: {
      type: String,
      default: "abc"
    },
    disableAutoComplete: {
      type: Boolean,
      default: false
    },
    isTargetSearch: {
      type: Boolean,
      default: false
    },
    currentIndex: {
      type: Number,
      default: null
    },
    componentRerender: {
      default: 'uniqui-id'
    }
  },

  mounted() {
    this.getMaxPopupWidth = this.$el.offsetWidth + "px";
    this.gb__selections.model = this.$props.defaultSelectedOptions;
    this.userName = window.config.userName;
  },
  data() {
    return {
      getMaxPopupWidth: "250px",
      gb__selections: {
        items: [],
        owner: null,
        model: this.$props.defaultSelectedOptions
      },
      options: [],
      operators: [
        { label: "OR", value: "|" },
        { label: "AND", value: "&" },
        { label: "NOT", value: "!" }
      ],
      truncateThreshold: 10,
      showReportPopup: false,
      detailsDialog: false,
      andList: [],
      orList: [],
      notList: [],
      autoCompleteUserEnteredValue: "",
      showReportPopup: false,
      filteredAutocompleteOptions: [],
      curUserSelection: "",
      userName: ""
    };
  },
  methods: {
    chipRemoveEvent(scope) {
      // handle chip delete scenario with binding already in the search
      let nonEmptyValues = 0;

      let showAlert = false;
      let showAlertActivityTypes = false;
      this.$store.state.simpleSearch.simpleSearchRowData.forEach(item => {
        if (item.simpleSearchAutoComplete.length == 1) {
          nonEmptyValues++;
        }
      });
      if (nonEmptyValues == 2) {
        this.$store.state.simpleSearch.simpleSearchRowData.forEach(
          searchRow => {
            // check if selected value is assay and proceed
            if (searchRow.simpleSearchSelectedValue == "Assay") {
              let isBindingSelected = searchRow.simpleSearchAutoComplete.filter(
                p =>
                  p.label.toLowerCase().indexOf("b") == 0 ||
                  p.label.toLowerCase().indexOf("f") == 0
              );

              if (isBindingSelected.length > 0) {
                showAlert = true;
              }
            }
          }
        );
      }
      // to show alert except assay
      if(scope.opt.assayType) {
        showAlert = false;
      }
      // to show alert except assay
      
      // CHECK IF ACTIVITYtYPES  IS SELECTED START  
      let activityTypesData = this.$store.state.simpleSearch.simpleSearchRowData.filter(
        p => p.simpleSearchSelectedValue == "ActivityTypes" ||
        p.simpleSearchSelectedValue == "EndpointTreeView"
      );
      let doShowAlertMsgForActive = 0;
      if (activityTypesData.length > 0 && nonEmptyValues == 2) {
        for (
          let index = 0;
          index < this.$store.state.simpleSearch.simpleSearchRowData.length;
          index++
        ) {
          if (
            this.$store.state.simpleSearch.simpleSearchRowData[index][
              "hasValue"
            ]
          ) {
            for (let item of this.$store.state.simpleSearch.simpleSearchOptions) {
              if (item.value == this.$store.state.simpleSearch.simpleSearchRowData[index][
                "simpleSearchSelectedValue"]) {
               // if(item.parent == 'input') {
                  doShowAlertMsgForActive += 1;
               // }
              }
            }
          }
        }
      }
      if(doShowAlertMsgForActive == 2) {
        for (let item of this.$store.state.simpleSearch.simpleSearchRowData) { 
             if (item.simpleSearchSelectedValue != "ActivityTypes" && item.simpleSearchSelectedValue != "EndpointTreeView") {
                  showAlertActivityTypes = true;
              }
          }
      }
      if(activityTypesData.length == 1 && nonEmptyValues==1) {
        for (let item of this.$store.state.simpleSearch.simpleSearchRowData) { 
             if (item.simpleSearchSelectedValue != "ActivityTypes" && item.simpleSearchSelectedValue != "EndpointTreeView") {
                  showAlertActivityTypes = true;
              }
          }
      }
      if(scope.opt.activityType) {
        showAlertActivityTypes = false;
      }
      // CHECK IF ACTIVITYtYPES  IS SELECTED END
      
      if (showAlert) {
        this.$emit("show-alert-popup", true);
        return;
      } else if(showAlertActivityTypes) {
        this.$emit("show-alert-popup-activity", true);
        return;
      }else {
        return scope.removeAtIndex(scope.index);
      }
    },
    chipClicked(e) {
      e.stopPropagation();
    },
    removeNotCondition(oper, scope) {
      return !(oper == "!" && scope.index == 0);
    },
    reportClicked() {
      this.showReportPopup = true;
    },
    showAutoCompleteSelectedDetails() {
      this.detailsDialog = true;
      this.andList = [];
      this.orList = [];
      this.notList = [];

      let filterAnd = this.gb__selections.model.filter(p => p.operator == "&");
      this.andList = JSON.parse(JSON.stringify(filterAnd));
      let filterOr = this.gb__selections.model.filter(p => p.operator == "|");
      this.orList = JSON.parse(JSON.stringify(filterOr));
      let filterNot = this.gb__selections.model.filter(p => p.operator == "!");
      this.notList = JSON.parse(JSON.stringify(filterNot));
    },
    changeOperator(item, newOperator) {
      item.opt.operator = newOperator;
      this.emitUserSelectedAutoCompleteValues(this.gb__selections);
    },
    loadData(query) {
      let filtered = [];

      for (let i = 0; i < this.filteredAutocompleteOptions.length; i++) {
        let res = this.filteredAutocompleteOptions[i];
        if (
          res &&
          res.label &&
          res.label.toLowerCase().indexOf(query.toLowerCase()) >= 0
        ) {
          filtered.push(res);
        }
      }
      //Client requirement performance issue fix
      if (this.$props.selectedValue == "Assay") {
        for (let item of this.$store.state.simpleSearch.simpleSearchRowData) {
          /* Handle for 2 rows scenario */
          if (item.hasValue && item.simpleSearchSelectedValue != "Assay") {
            this.options = this.$props.isTargetSearch
              ? this.groupTargetOptionsByCommonName(filtered, query)
              : filtered;

            return this.options;
          }
          if (!item.hasValue && item.simpleSearchSelectedValue != "Assay") {
            let filterAssay = [];
            filterAssay = filtered.filter(
              p =>
                p.label.toLowerCase().indexOf("b") == 0 ||
                p.label.toLowerCase().indexOf("f") == 0
            );
            for (let i of filterAssay) {
              let ind = filtered.indexOf(i);
              filtered.splice(ind, 1);
            }
          } else if (
            /* Handle single row scenario when only assay is selected */
            !item.hasValue &&
            this.$store.state.simpleSearch.simpleSearchRowData.length == 1
          ) {
            let filterAssay = [];
            filterAssay = filtered.filter(
              p =>
                p.label.toLowerCase().indexOf("b") == 0 ||
                p.label.toLowerCase().indexOf("f") == 0
            );
            for (let i of filterAssay) {
              let ind = filtered.indexOf(i);
              filtered.splice(ind, 1);
            }
          } else if (
            /* Handle scenario when only for assay when row is deleted */
            item.simpleSearchSelectedValue == "Assay" &&
            this.currentIndex == 0 &&
            this.$store.state.simpleSearch.simpleSearchRowData.length == 1
          ) {
            let filterAssay = [];
            filterAssay = filtered.filter(
              p =>
                p.label.toLowerCase().indexOf("b") == 0 ||
                p.label.toLowerCase().indexOf("f") == 0
            );
            for (let i of filterAssay) {
              let ind = filtered.indexOf(i);
              filtered.splice(ind, 1);
            }
          }
        }
      }

      this.options = this.$props.isTargetSearch
        ? this.groupTargetOptionsByCommonName(filtered, query)
        : filtered;

      return this.options;
    },
    groupTargetOptionsByCommonName(options, query) {
      let newOptions = [];
      let compareOptions = [];
      options.forEach(item => {
        if (compareOptions.indexOf(item.commonName) == -1) {
          let newOp = JSON.parse(JSON.stringify(item));
          let abc = options.filter(
            curOp => curOp.commonName == item["commonName"]
          );
          newOp["sub_names"] = JSON.parse(JSON.stringify(abc));
          newOp["sub_names"] = this.sortAutoCompleteOptions(
            newOp["sub_names"],
            query
          );
          newOptions.push(newOp);

          newOp["label"] = item["commonName"];
          compareOptions.push(item["commonName"]);
        }
      });
      /* TO restrict duplicates which are already selected */
      let temp = JSON.parse(JSON.stringify(newOptions));
      for (var i = temp.length - 1; i >= 0; i--) {
        for (var j = 0; j < this.gb__selections.model.length; j++) {
          if (temp[i] && temp[i].label === this.gb__selections.model[j].label) {
            temp.splice(i, 1);
          }
        }
      }
      newOptions = JSON.parse(JSON.stringify(temp));

      return newOptions;
    },
    sortAutoCompleteOptions(data, query) {
      let filterStartWith;
      filterStartWith = data.filter(
        p => p.label.toLowerCase().indexOf(query.toLowerCase()) == 0
      );

      for (let i of filterStartWith) {
        let ind = data.indexOf(i);
        data.splice(ind, 1);
        data.unshift(i);
      }
      return data;
    },
    filterFn(val, update, abort) {
      this.curUserSelection = val;
      if (!this.$props.showOptionsOnFocus && val.length < this.$props.triggerApiAt) {
        abort();
        return;
      }
      let payload = {
        searchTerm: val,
        selectedOptionValue: this.$props.selectedValue,
        searchType: this.$props.searchType
      };
      store
        .dispatch("shared/GET_AUTO_COMPLETE_RESULTS", payload)
        .then(response => {
          // compare with selected values and remove duplicates
          for (var i = response.data.length - 1; i >= 0; i--) {
            for (var j = 0; j < this.gb__selections.model.length; j++) {
              if (
                response.data[i] &&
                response.data[i].value === this.gb__selections.model[j].value
              ) {
                response.data.splice(i, 1);
              }
            }
          }

          this.filteredAutocompleteOptions = response.data;
          this.emitUserSearchQuery(val);
          this.autoCompleteUserEnteredValue = val;
          update(() => {
            const needle = val.toLowerCase();
            return this.loadData(needle);
          });
        })
        .catch();
    },
    resetAutoCompleteValues() {
      // remove from autocomplete
      this.cleargb__Selections();
      // update store with relavent data.
      let curData =
        store.state.simpleSearch.simpleSearchRowData[
          store.state.advanceSearch.currentItemIndex
        ];
      /*  curData.hasValue = false; */
      this.$set(
        store.state.simpleSearch.simpleSearchRowData,
        store.state.advanceSearch.currentItemIndex,
        Object.assign(curData, {
          advanceSearch: {
            autoComplete: []
          }
        })
      );
      if (this.$props.searchType == "SIMPLE_SEARCH") {
        store.commit("simpleSearch/RESET_SIMPLE_SERACH_COUNT");
      }
    },
    emitUserSearchQuery(val) {
      this.$emit("autocomplete-user-entered-val", val);
    },
    emitUserSelectedAutoCompleteValues(updatedModel) {
      let model = JSON.parse(JSON.stringify(updatedModel.model));
      this.$emit("auto-complete-selection-change", model);
    },
    addSelection(item) {
      //if the owner reference is still null, set it to this item's parent
      //so that further selection is only allowed within the same container
      if (!this.gb__selections.owner) {
        this.gb__selections.owner = item.parentNode;
      }

      //or if that's already happened then compare it with this item's parent
      //and if they're not the same container, return to prevent selection
      // else if (this.gb__selections.owner != item.parentNode) {
      //   return;
      // }

      //set this item's grabbed state
      item.setAttribute("aria-grabbed", "true");

      //add it to the items array
      this.gb__selections.items.push(item);
      this.gb__selections.model.push(
        JSON.parse(item.getAttribute("data-value"))
      );

      this.emitUserSelectedAutoCompleteValues(this.gb__selections);
    },
    removeChip(e) {
      for (var len = this.gb__selections.model.length, i = 0; i < len; i++) {
        if (this.gb__selections.model[i]["value"] == e.value[0]["value"]) {
          this.gb__selections.model.splice(i, 1);
          break;
        }
      }

      this.emitUserSelectedAutoCompleteValues(this.gb__selections);
    },
    removeSelection(item, uniqueId) {
      //reset this item's grabbed state
      item.setAttribute("aria-grabbed", "false");

      //then find and remove this item from the existing items array
      for (var len = this.gb__selections.items.length, i = 0; i < len; i++) {
        if (this.gb__selections.items[i] == item) {
          this.gb__selections.items.splice(i, 1);
          this.gb__selections.model.splice(i, 1);
          break;
        }
      }

      this.emitUserSelectedAutoCompleteValues(this.gb__selections);
    },
    cleargb__Selections() {
      //if we have any selected items
      if (this.gb__selections.items.length) {
        //reset the owner reference
        this.gb__selections.owner = null;

        //reset the grabbed state on every selected item
        for (var len = this.gb__selections.items.length, i = 0; i < len; i++) {
          this.gb__selections.items[i].setAttribute("aria-grabbed", "false");
        }

        //then reset the items array
        this.gb__selections.items = [];
      }
      this.gb__selections.model = [];

      this.emitUserSelectedAutoCompleteValues(this.gb__selections);
    },
    hasModifier(e) {
      return e.ctrlKey || e.metaKey || e.shiftKey || e.altKey;
    },
    mouseDownEvent(e) {
      //if the element is a draggable item
      if (e.target.getAttribute("draggable")) {
        //if the multiple selection modifier is not pressed
        //and the item's grabbed state is currently false
        if (
          !this.hasModifier(e) &&
          e.target.getAttribute("aria-grabbed") == "false"
        ) {
          //clear all existing gb__selections
          if (!e.isCustomEvent) {
            // this.cleargb__Selections();
          }

          //then add this new selection
          this.addSelection(e.target);

          if (e.ctrlKey && e.isCustomEvent) {
            this.cleanUpAutoComplete(e);
          }
        }
      }

      //else [if the element is anything else]
      //and the selection modifier is not pressed
      else if (!this.hasModifier(e)) {
        //clear all existing gb__selections
        this.cleargb__Selections();
      }
    },
    mouseUpEvent(e) {
      //if the element is a draggable item
      //and the multipler selection modifier is pressed
      if (
        e.target.getAttribute("draggable") &&
        (this.hasModifier(e) || e.isCustomEvent)
      ) {
        //if the item's grabbed state is currently true
        if (e.target.getAttribute("aria-grabbed") == "true") {
          //unselect this item
          this.removeSelection(e.target);

          //if that was the only selected item
          //then reset the owner container reference
          if (!this.gb__selections.items.length) {
            this.gb__selections.owner = null;
          }
        }

        //else [if the item's grabbed state is false]
        else {
          //add this additional selection
          this.addSelection(e.target);
        }
      }

      if (!event.isCustomEvent) {
        this.cleanUpAutoComplete(e);
      }
    },
    cleanUpAutoComplete(e) {
      if (!this.hasModifier(e)) {
        this.$refs.autocomplete.__closePopup();
      }

      this.$refs.autocomplete.inputValue = "";
    },
    dragStartEvent(e) {
      //if the element's parent is not the owner, then block this event
      // if(this.gb__selections.owner != e.target.parentNode)
      // {
      //     e.preventDefault();
      //     return;
      // }

      //[else] if the multiple selection modifier is pressed
      //and the item's grabbed state is currently false
      if (
        this.hasModifier(e) &&
        e.target.getAttribute("aria-grabbed") == "false"
      ) {
        //add this additional selection
        this.addSelection(e.target);
      }

      //we don't need the transfer data, but we have to define something
      //otherwise the drop action won't work at all in firefox
      //most browsers support the proper mime-type syntax, eg. "text/plain"
      //but we have to use this incorrect syntax for the benefit of IE10+
      e.dataTransfer.setData("text", "");
    },
    dragEndEvent(e) {
      //if the element's parent is not the owner, then block this event
      if (this.gb__selections.owner != e.target.parentNode) {
        e.preventDefault();
        return;
      }

      //[else] if the multiple selection modifier is pressed
      //and the item's grabbed state is currently false
      if (
        this.hasModifier(e) &&
        e.target.getAttribute("aria-grabbed") == "false"
      ) {
        //add this additional selection
        this.addSelection(e.target);
      }

      //we don't need the transfer data, but we have to define something
      //otherwise the drop action won't work at all in firefox
      //most browsers support the proper mime-type syntax, eg. "text/plain"
      //but we have to use this incorrect syntax for the benefit of IE10+
      e.dataTransfer.setData("text", "");

      this.cleanUpAutoComplete(e);
    },
    dragOverEvent(e) {
      //if the element's parent is not the owner, then block this event
      // if (this.gb__selections.owner != e.target.parentNode) {
      //   e.preventDefault();
      //   return;
      // }

      //[else] if the multiple selection modifier is pressed
      //and the item's grabbed state is currently false
      if (
        this.hasModifier(e) &&
        e.target.getAttribute("aria-grabbed") == "false"
      ) {
        //add this additional selection
        this.addSelection(e.target);
      }

      //we don't need the transfer data, but we have to define something
      //otherwise the drop action won't work at all in firefox
      //most browsers support the proper mime-type syntax, eg. "text/plain"
      //but we have to use this incorrect syntax for the benefit of IE10+
      e.dataTransfer.setData("text", "");
    },
    keyDownEvent(e) {
      //if the element is a grabbable item
      if (e.target.getAttribute("aria-grabbed")) {
        //Space is the selection or unselection keystroke
        if (e.keyCode == 32) {
          //if the multiple selection modifier is pressed
          if (this.hasModifier(e)) {
            //if the item's grabbed state is currently true
            if (e.target.getAttribute("aria-grabbed") == "true") {
              //unselect this item
              this.removeSelection(e.target);

              //if that was the only selected item
              //then reset the owner container reference to null
              if (!this.gb__selections.items.length) {
                this.gb__selections.owner = null;
              }
            }

            //else [if its grabbed state is currently false]
            else {
              //add this additional selection
              this.addSelection(e.target);
            }
          }

          //else [if the multiple selection modifier is not pressed]
          //and the item's grabbed state is currently false
          else if (e.target.getAttribute("aria-grabbed") == "false") {
            //clear all existing gb__selections
            this.cleargb__Selections();

            //then add this new selection
            this.addSelection(e.target);
          }

          //then prevent default to supress any native actions
          e.preventDefault();
        }
      }

      //Escape is the abort keystroke (for any target element)
      if (e.keyCode == 27) {
        //if we have any selected items
        if (this.gb__selections.items.length) {
          //clear all existing gb__selections
          this.cleargb__Selections();

          //but don't prevent default so native actions still occur
        }
      }
    },
    ctrlKeyUpEvent(e) {
      if (!e.ctrlKey) {
        this.cleanUpAutoComplete(e);
      }
    },
    getSiblings(elem) {
      // Setup siblings array and get the first sibling
      var siblings = [];
      var sibling = elem.parentNode.firstChild;

      // Loop through each sibling and push to the array
      while (sibling) {
        if (sibling.nodeType === 1 && sibling !== elem) {
          siblings.push(sibling);
        }
        sibling = sibling.nextSibling;
      }

      return siblings;
    },
    applyHighlighting(e, id, propagateTo) {
      let el = document.getElementById(id);
      if (el.classList && !el.classList.contains("highlighted")) {
        el.classList.add("highlighted");
        let node = document.querySelector(
          '[data-stdnameId="' + propagateTo + '"]'
        );
        let event = document.createEvent("MouseEvents");
        event.initEvent("mousedown", true, true);
        event.isCustomEvent = true;
        event.ctrlKey = e.ctrlKey;
        node.dispatchEvent(event);
        this.ctrlKeyUpEvent(e);
      } else {
        el.classList.remove("highlighted");

        var x = document.getElementsByClassName(propagateTo);
        var i;
        var isClassNotExists = false;
        for (i = 0; i < x.length; i++) {
          if (x[i]["innerHTML"].includes("highlighted")) {
            return false;
          }
          isClassNotExists = true;
        }

        if (isClassNotExists) {
          let node = document.querySelector(
            '[data-stdnameId="' + propagateTo + '"]'
          );
          let event = document.createEvent("MouseEvents");
          event.initEvent("mouseup", true, true);
          event.isCustomEvent = true;
          event.ctrlKey = e.ctrlKey;
          node.dispatchEvent(event);
          this.ctrlKeyUpEvent(e);
        }
      }
    }
  }
});
