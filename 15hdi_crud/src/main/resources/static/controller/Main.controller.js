sap.ui.define(
    ["sap/ui/core/mvc/Controller",
        "jquery.sap.global",
        "subho/util/service",
        "sap/m/MessageBox"
    ],
    function(Controller, jQuery, service, MessageBox) {
        return Controller.extend("subho.controller.Main", {
            onInit: function() {
                // Step:2 Create a new UI5 Model which contains data
                var oModel = new sap.ui.model.json.JSONModel();
                oModel.setData({
                    "postPayload": {
                        "id" : "",
                        "companyName": "",
                        "contactName": "",
                        "lastName": "",
                        "website": "",
                        "email": "",
                        "vstatus": "A",
                        "gstNumber": ""
                    },
                    "vendor": {}
                });
                //Set the Model object to the entire App level
                sap.ui.getCore().setModel(oModel);
            },

            onSave: function() {
                var oModel = sap.ui.getCore().getModel();
                var payload = oModel.getProperty("/postPayload");
                service.callService("/vendor", "POST", payload).then(function() {
                    MessageBox.confirm("Saved successfully");
                }).catch(function() {
                    MessageBox.error("POST failed");
                });
            },

            onLoadData: function() {
                var that = this;
                service.callService("/vendors", "GET", {}).then(function(data) {
                    //console.log(data);
                    // Step:1 Get the object of the Table
                    var oTable = that.getView().byId("idTable");
                    var oModel = sap.ui.getCore().getModel();
                    oModel.setProperty("/vendor", data);
                    sap.ui.getCore().setModel(oModel);

                    // Step:4 Bind aggregation of the table
                    oTable.bindRows("/vendor");
                }).catch(function(oError) {

                });
            }
        });
    });