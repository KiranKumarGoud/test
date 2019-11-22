Vue.component("target-treeview-search", {
  template: `<div class="modal-dialog modal-big-lg" role="document">
  <div class="modal-content treeview-search">
      <div class="modal-header">
        <h5 class="modal-title txt-wht-col" id="exampleModalLabel">Grouped Actions</h5>
        <button @click="closePopup()" type="button" data-dismiss="modal" aria-label="Close"> 
        <span aria-hidden="true"><i class="fa fa-times"></i></span> </button>
      </div>
      <div class="modal-body">
          <div class="m-portlet__body">
              <div class="tab-content">
                  <div class="tab-pane active" id="m_tabs_6_1" role="tabpanel"> 
                      <!-- Tab 2 Container part -->
                      <div>
                          <div class="m-portlet__body  m-portlet__body--no-padding">
                              <div class="row m-row--no-padding m-row--col-separator-xl">
                                  <div class="col-xl-12"> 
                                      <!--begin:: Widgets/Daily Sales-->
                                      <div class="m-widget14">
                                          <div class="m-widget14__header m--margin-bottom-30">
                                              <h3 class="m-widget14__title"> Target Tree view Input </h3>                                
                                          </div>
                                          <div class="row col-12">
                                              <div class="col-6">
                                                  <auto-complete
                                                      selected-value="TargetTreeView"
                                                      search-type="TREE_VIEW_SEARCH"
                                                      ref="autoComplete"
                                                      :default-selected-options="$store.state.treeViewSearch.userTreeSearchKey"
                                                      :disable-auto-complete="false"
                                                      :hide-operators="true"
                                                      @auto-complete-selection-change="autoCompleteChange($event)"
                                                  />
                                              </div>
                                              <q-btn
                                                  @click="searchTreeView"
                                                  class="col-1 bg-green"
                                                  dense
                                                  flat
                                                  label="Enter"
                                              >
                                              </q-btn>
                                          </div>
                                          <!--end:: Widgets/Daily Sales--> 
                                      </div>  
                                      <q-splitter v-model="splitterModel" style="height: 400px;min-width: 800px">
                                        <template v-slot:before>
                                          <!--
                                          <q-btn
                                            round
                                            style="position: absolute; right: 19px; top: 10px;z-index:1000"
                                            size="md"
                                            @click="getLazyLoadItems(0)"
                                            icon="fa fa-refresh"
                                          >
                                          </q-btn> -->
                                          <div class="q-pa-md" v-show="showTreeView">
                                            <q-inner-loading :showing="showTreeLoading" style="background: #9a9caf38">
                                            loading ...
                                            </q-inner-loading>
                                            <transition appear enter-active-class="animated fadeIn" leave-active-class="animated fadeOut">
                                              <q-tree
                                                ref="treeViewComponent"
                                                tick-strategy="strict"
                                                :ticked.sync="curSelectedNodeIds"
                                                :nodes="$store.state.treeViewSearch.totalTreeNodes"
                                                node-key="ontologyId"
                                                :no-results-label="noDataFoundMsg"
                                                @lazy-load="lazyLoadItemsEvent"
                                              >
                                                <template v-slot:default-header="prop">
                                                <div class="row items-center">
                                                <div v-if="prop.node && copyUserSearchValue" v-html="highlightText(prop.node.label)"></div>
                                                <div v-if="prop.node && !copyUserSearchValue" >{{prop.node.label}}</div>
                                                </div>
                                                </template>
                                              </q-tree>
                                            </transition>
                                          </div>
                                          <div v-show="!showTreeView" class="flex flex-center" style="height: 400px;">
                                            {{noDataFoundMsg}}
                                          </div>
                                        </template>
                                        <template v-slot:after>
                                          <q-card bordered class="q-ma-sm">
                                            <q-card-section>
                                              <div class="text-h6">Selected Items</div>
                                            </q-card-section>
                                            <q-separator inset />
                                            <q-card-section>
                                              <ol class="selected-list">
                                                <li v-for="item in $store.state.treeViewSearch.allSelectedNodes">
                                                  <b>
                                                    <q-chip
                                                      square
                                                      class="bg-white"
                                                      removable
                                                      @remove="deleteCurrentNode(item.ontologyId)"
                                                    >
                                                      <div>
                                                        <div v-if="item.label.length > labelTruncateLimit">
                                                        {{ item.label.substring(0,labelTruncateLimit) }}
                                                        <span>
                                                        ...
                                                        <q-tooltip>{{item.label}}</q-tooltip>
                                                        </span>
                                                        </div>
                                                        <div v-else>{{item.label}}</div>
                                                      </div>
                                                    </q-chip>
                                                  </b>
                                                </li>
                                              </ol>
                                              <div class="flex flex-center" v-if="$store.state.treeViewSearch.allSelectedNodes.length == 0">Selected Items Will display here.</div> 
                                            </q-card-section>
                                          </q-card>
                                        </template>
                                      </q-splitter>                     
                                  </div>
                              </div>
                          </div>
                      </div>            
                  </div>
              </div>      
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-danger btn-sm"
              :disabled="disableClear"
              @click="resetChanges()"
              >
              Clear
            </button>
            <button
              type="button"
              :disabled="disableAdd"
              class="btn btn-excelra btn-sm"
              @click="confirm()"
              >
              Add
            </button>
        </div>
      </div>
      <app-loader v-if="showLoading"></app-loader>
  </div>
</div>
  `,
  mounted() {

    setTimeout(()=> {
        let curItemIndex = this.$store.state.advanceSearch.currentItemIndex;
      
        if(this.$store.state.simpleSearch.simpleSearchRowData[
          curItemIndex
        ]['treeViewSearch'] && this.$store.state.simpleSearch.simpleSearchRowData[
          curItemIndex
        ]['treeViewSearch']['selectedNodeIds']) {
          
          this.$store.state.treeViewSearch = JSON.parse(JSON.stringify(this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]['treeViewSearch']))
          this.skipWatchflag = true
          this.searchTreeView();
          this.$refs.treeViewComponent.setTicked(this.$store.state.treeViewSearch.selectedNodeIds, true)
        } else {
          this.getLazyLoadItems(0);
        }
    }, 500)
      
  },
  data() {
    return {
      showLoading: false,
      showTreeLoading: false,
      labelTruncateLimit: 45,
      curSelectedNodeIds: [],
      copyUserSearchValue: '',
      postDataParms: [],
      splitterModel: 50,
      searchCountData: [],
      skipWatchflag: false,
      showTreeView: true,
      noDataFoundMsg: 'No reselt set found to show tree view.'
    }
  },
  watch: {
    curSelectedNodeIds(n,o) {
      setTimeout(() => {
        // user added new node

        if(this.skipWatchflag) {
          this.skipWatchflag = false;
          return ;
        }

        let modifiedNodeKey =  this.arrDiff(n,o)

        if(n.length > o.length) {
          let newNode = this.$refs.treeViewComponent.getNodeByKey(modifiedNodeKey[0]);
          this.$store.state.treeViewSearch.allSelectedNodes.push(newNode)
        }else{
          // user removed existing node
          let deletedNodeKey = modifiedNodeKey[0]
          this.deleteCurrentNode(deletedNodeKey);
        }
      },0)
    }
  },
  methods: {
    autoCompleteChange(event) {
      this.$store.state.treeViewSearch.userTreeSearchKey = event;
    },
    arrDiff(arr1, arr2) {
      var arrays = [arr1, arr2].sort((a, b) => a.length - b.length);
      var smallSet = new Set(arrays[0]);
  
      return arrays[1].filter(x => !smallSet.has(x));
    },
    deleteCurrentNode(key) {
      this.$store.state.treeViewSearch.allSelectedNodes.forEach((item,index) => {
        if(item.ontologyId == key) {
          this.$refs.treeViewComponent.setTicked([key], false)
          this.$store.state.treeViewSearch.allSelectedNodes.splice(index,1)
        }
      })
    },
    collapseAllTreeNodes() {
      this.$refs.treeViewComponent.collapseAll()
    },
    highlightText(text) {
      if(!text) {
        return '' 
      }

      if(JSON.stringify(this.postDataParms).indexOf(JSON.stringify(text)) > -1) {
        return text.replace(text,'<span class="text-weight-bold text-green-9">' + text + '</span>')
      }else{
        return text;
      }
    },
    expandTreeAndHighlightSearchedKey(parentNode, userSearchKey) {
        parentNode.lazy = false;
        if(parentNode.children && parentNode.children.length > 0) {
          parentNode.children.forEach(childNode => {
            this.expandTreeAndHighlightSearchedKey(childNode, userSearchKey);
          })
        }
        this.$refs.treeViewComponent.setExpanded(parentNode.ontologyId, true)
    },
    searchTreeView(){
      this.showTreeLoading = true
      this.postDataParms = []
      this.showTreeView = true;
      this.copyUserSearchValue = this.$store.state.treeViewSearch.userTreeSearchKey

      // if(this.copyUserSearchValue.length == 0) {
      //   return ;
      // }
       
        // this.copyUserSearchValue.forEach(item => {
        //   this.postDataParms.push(item.value)
        // })

        // display selected child nodes start
        if(this.copyUserSearchValue.length == 0) {
          this.$store.state.treeViewSearch.allSelectedNodes.forEach(node => {
            this.postDataParms.push(node.label)
          })
        } else {
          this.copyUserSearchValue.forEach(item => {
            this.postDataParms.push(item.value)
          })
        }
        // display selected child nodes end

        this.$store.dispatch('treeViewSearch/SEARCH_TREE_VIEW_ITEMS', this.postDataParms)
        .then(response => {
          if(response.data.length > 0) {
            this.collapseAllTreeNodes();
            // attach all the searched nodes to the existing tree view
            response.data.forEach(node => {
              let parentNode = this.$refs.treeViewComponent.getNodeByKey(node.ontologyId)
              parentNode.children  = node.children
              this.expandTreeAndHighlightSearchedKey(parentNode, this.copyUserSearchValue)
            })

            this.$refs.treeViewComponent.setTicked(this.$store.state.treeViewSearch.selectedNodeIds, true)

            this.showTreeLoading = false;
          }else{
            this.showTreeView = false;
            this.showTreeLoading = false;
          }
        })
        .catch(e => {
          console.log(e)
          this.showTreeLoading = false;
          
        })
      
    },
    lazyLoadItemsEvent({ node, key, done, fail }){
      this.$store.dispatch('treeViewSearch/GET_LAZY_LOAD_TREE_ITEMS', node.ontologyId)
      .then(response => {
        if(response && response.data.length > 0) {
          let children = response.data.map(item => {
            delete item.children
            item.lazy = true
            return item;
          })
          done(children)
        }else{
          done([])
        }
      })
      .catch(e => {
        console.log(e)
      })
    },
    getLazyLoadItems(id){
      this.$store.dispatch('treeViewSearch/GET_LAZY_LOAD_TREE_ITEMS', id)
      .then(response => {
        if(!response) {
          return ;
        }
        
        let children = response.data.map(item => {
          delete item.children
          item.lazy = true
          return item;
        })
        if(id == 0) {
          this.$store.state.treeViewSearch.totalTreeNodes = children
        }else {
          return children;
        }
      })
      .catch(e => {
        console.log(e)
      })
    },
    confirm() {
      this.$bvModal
        .msgBoxConfirm("Are you sure?", {
          centered: true
        })
        .then(value => {
          if (!value) {
            return;
          }

          let curItemIndex = this.$store.state.advanceSearch.currentItemIndex;
           // set current select option clear data and add item again
           if(!this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]['simpleSearchSelectedValue']) {
          this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ].simpleSearchSelectedValue = this.$store.state.treeViewSearch.currentTreeViewSelected;
        }
        // set current select option clear data and add item again
          let curAdvanceSearch = this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]["advanceSearch"];

          // attach all selected node ids to the treeViewSearch state
          this.$store.state.treeViewSearch.selectedNodeIds = this.curSelectedNodeIds
          // set it to simple search row data.
          this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]["treeViewSearch"] = JSON.parse(JSON.stringify(this.$store.state.treeViewSearch));

          this.$store.state.simpleSearch.simpleSearchRowData[
            this.$store.state.advanceSearch.currentItemIndex
          ]["hasValue"] = true;

          this.getSearchResultsAndCount();
          this.$emit("adv-popup-closed", true);
         
        })
        .catch(err => {
          console.log(err)
          // An error occurred
        });
    },
    resetChanges() {
      this.$store.state.treeViewSearch.userTreeSearchKey = [];
      this.$store.state.treeViewSearch.copyUserSearchValue = [];
      if(this.$refs.autoComplete) {
        this.$refs.autoComplete.resetAutoCompleteValues();
      }
      
      let curItemIndex = this.$store.state.advanceSearch.currentItemIndex;
  
      this.$store.state.simpleSearch.simpleSearchRowData[
        curItemIndex
      ]['treeViewSearch'] = {}
      this.$store.state.simpleSearch.simpleSearchRowData[
        curItemIndex
      ]['simpleSearchSelectedValue'] = null

       // disable add button
       this.$store.state.simpleSearch.simpleSearchRowData[
        this.$store.state.advanceSearch.currentItemIndex
      ]["hasValue"] = false;
      // disable add button
      

      this.curSelectedNodeIds = [];
      this.$store.state.treeViewSearch.allSelectedNodes = [];
      this.$store.state.treeViewSearch.selectedNodeIds = [];
      this.collapseAllTreeNodes();
      this.getLazyLoadItems(0)
      this.getSearchResultsAndCount();
    },
    getInitalCounts() {
      this.$store
        .dispatch("simpleSearch/GET_SEARCH_DEFAULT_COUNTS")
        .then(response => {
          if (response.status == 200) {
            this.$store.state.simpleSearch.searchCount = response.data;
          }
        });
    },
    closePopup() {
       //if not selected nodes delete row start
       this.resetTreeViewData();
       //if not selected nodes delete row end
      this.$emit("adv-popup-closed", true);
    },
    resetTreeViewData() {
      let curItemIndex = this.$store.state.advanceSearch.currentItemIndex;
      let item = this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex].simpleSearchSelectedValue
      if(this.$store.state.treeViewSearch.selectedNodeIds.length == 0) {
          this.resetChanges();
          if(this.$store.state.simpleSearch.simpleSearchRowData.length > 1) {
            this.$store.state.simpleSearch.simpleSearchRowData.splice(curItemIndex, 1);
          }
          if (
          curItemIndex > 0 &&
          this.$store.state.simpleSearch.simpleSearchRowData.length == curItemIndex
        ) {
          let ind = curItemIndex - 1;

          this.$store.state.simpleSearch.simpleSearchRowData[ind][
            "disableAllFields"
          ] = false;
          this.$store.state.simpleSearch.simpleSearchRowData[ind][
            "disableSimpleSearchSelect"
          ] = false;

          // for enable activity types enable option start
          if(this.$store.state.simpleSearch.simpleSearchRowData[ind][
            "simpleSearchSelectedValue"
          ] == 'ActivityTypes') {
            for (let item of this.$store.state.simpleSearch.simpleSearchOptions) { 
              if (item.value == "EndpointTreeView") {
              item.isDisabled = false;
              return
              }
            }
          }
          if(this.$store.state.simpleSearch.simpleSearchRowData[ind][
            "simpleSearchSelectedValue"
          ] == 'EndpointTreeView') {
            for (let item of this.$store.state.simpleSearch.simpleSearchOptions) { 
              if (item.value == "ActivityTypes") {
              item.isDisabled = false;
              return
              }
          }
        }
        // for enable activity types enable option end
        }
      }
      // DELETE ROWS UPDATED START
      if(item) {
      let fil = [];
      let rd = item;
      this.$store.state.simpleSearch.simpleSearchOptions.filter(
        p =>  {
          if(rd == 'ActivityTypes' || rd == 'EndpointTreeView') {
              if(p.value == 'ActivityTypes' || p.value == 'EndpointTreeView') {
                fil.push(p);
              }
          } else if(rd == 'Indication' || rd == 'IndicationTreeView') {
              if(p.value == 'Indication' || p.value == 'IndicationTreeView') {
                fil.push(p);
              }
          } else if(rd == 'Target' || rd == 'TargetTreeView') {
              if(p.value == 'Target' || p.value == 'TargetTreeView') {
                fil.push(p);
              }
          } else if(p.value == item) {
            fil = [];
            fil.push(p);
          }
        }
      );
      if (fil.length) {
        fil.filter((v,i) => {
          fil[i]["isDisabled"] = false;
        });
      }
    }
    if(this.$store.state.simpleSearch.simpleSearchRowData.length == 1) {
      for (let item of this.$store.state.simpleSearch.simpleSearchOptions) { 
           if (item.value == "ActivityTypes" || item.value == "EndpointTreeView") {
            item.isDisabled = true;
            }
          if (item.value == "Indication" || item.value == "IndicationTreeView") {
            item.isDisabled = false;
            }
          if (item.value == "Target" || item.value == "TargetTreeView") {
            item.isDisabled = false;
            }
        }
   }
      // DELETE ROWS UPDATED START END
    },
    getSearchResultsAndCount() {
      this.$store.state.advanceSearch.showCountsLoader = true;
      let postParam = [];
      this.$store.commit("simpleSearch/GET_SEARCH_FINAL_PAYLOAD");
      postParam = this.$store.state.simpleSearch.searchPostObj;
      this.showLoading = true;
      this.$store
        .dispatch("simpleSearch/GET_SEARCH_RESULTS_AND_COUNT", postParam)
        .then(res => {
          this.showLoading = false;
          this.searchCountData = res.data;
          this.$store.commit("simpleSearch/SET_SEARCH_COUNT", res.data);
          this.$store.state.advanceSearch.showCountsLoader = false;
          this.saveStoreData();
          // this.closePopup();
        })
        .catch(e => {
          this.$store.state.advanceSearch.showCountsLoader = false;
          return e.response;
        });
    },
    saveStoreData() {
      let postData = {
        advanceSearch: JSON.parse(
          JSON.stringify(this.$store.state.advanceSearch)
        ),
        shared: JSON.parse(JSON.stringify(this.$store.state.shared)),
        simpleSearch: JSON.parse(
          JSON.stringify(this.$store.state.simpleSearch)
        ),
        structureSearch: JSON.parse(
          JSON.stringify(this.$store.state.structureSearch)
        ),
        user: JSON.parse(JSON.stringify(this.$store.state.user))
      };
      localStorage.setItem("simpleSearchStore", JSON.stringify(postData));
    },
  },
  computed: {
    disableAdd(){
      let disable = true
      if(this.$store.state.treeViewSearch.allSelectedNodes.length > 0) {
        disable = false
      }
      return disable
    },
    disableClear(){
      let disable = true
      if(this.$store.state.treeViewSearch.allSelectedNodes.length > 0) {
        disable = false
      }
      return disable
    },
    getAdvanceSelectedValue() {
      return this.$store.getters["advanceSearch/getAdvanceSelectedValue"];
    },
    getAutocompleteFieldValue() {
      return this.$store.getters["advanceSearch/getAutocompleteFieldValue"];
    },
    getAllSelectedOptionValues() {

      return this.$store.getters["simpleSearch/getAllSelectedOptionValues"];
    }
  }
});
