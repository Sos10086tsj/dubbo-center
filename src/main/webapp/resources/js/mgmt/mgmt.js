dubbocenter.mgmt = {
	grid : null,
	
	init : function(){
		
		var height = $(window).height();
		var width = $(window).width();
		
		var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
			saveBtnText: '保存', 
        	cancelBtnText: "取消", 
       	 	autoCancel: false, 
        	clicksToEdit:2 ,
        	listeners : {
        		edit : function( editor, context, eOpts){
        			var progress = dubbocenter.progressBar('正在修改，请稍后...');
        			progress.show();
        			var record = context.record;
        			Ext.Ajax.request({
	        			url : ctx + '/mgmt/update',
	        			method : 'post', 
	        			params : {
	        				jobId : record.get('jobId'),
	        				jobDescription:record.get('jobDescription'),
	        				//status:record.get('status'),
	        				cmdStartLocation:record.get('cmdStartLocation'),
	        				cmdStopLoacation:record.get('cmdStopLoacation'),
	        				checkInterval:record.get('checkInterval')
	        			},
	        			success : function(response){
	        				progress.hide();
	        				var result = Ext.decode(response.responseText);
	        				if(result.success){
	        					dubbocenter.warningResult('操作提示','保存成功');
	        				}else{
	        					dubbocenter.warningResult('操作提示',result.errorMessage);
	        				}
	        				dubbocenter.mgmt.grid.store.reload();
	        			},
	        			failure : function(response, opts){
	        				progress.hide();
	        				dubbocenter.warningResult('操作提示','网络异常，保存失败');
	        			}
	        		});
        		}
        	}
		});
		
		var store = dubbocenter.mgmt.store.initStore();
		var selModel = Ext.create('Ext.selection.CheckboxModel');
		
		dubbocenter.mgmt.grid = Ext.create('Ext.grid.Panel', {
			store : store,
			columns : [
			    {
			    	header: 'Job#',
			    	dataIndex: 'jobId',
			    	flex: 1,
			    	editor: {
			    		allowBlank: false
			    	}
			    },
			    {
			    	header: '任务描述',
			    	dataIndex: 'jobDescription',
			    	flex: 1,
			    	editor: {
			    		allowBlank: false
			    	}
			    },
			    {
			    	header: '状态',
			    	dataIndex: 'status',
			    	flex: 1,
			    	editable : false
			    },
			    {
			    	header: '启动脚本路径',
			    	dataIndex: 'cmdStartLocation',
			    	flex: 1,
			    	editor: {
			    		allowBlank: false
			    	}
			    },
			    {
			    	header: '停止脚本路径',
			    	dataIndex: 'cmdStopLoacation',
			    	flex: 1,
			    	editor: {
			    		allowBlank: false
			    	}
			    },
			    {
			    	header: '服务检查周期(ms)',
			    	dataIndex: 'checkInterval',
			    	flex: 1,
			    	editor: {
			    		allowBlank: false,
			    		xtype: 'combobox',
			    		store : dubbocenter.mgmt.store.initIntervalStore(),
			    		queryMode: 'local',
			    	    displayField: 'label',
			    	    valueField: 'value',
			    	    editable:false
			    	}
			    }
			],
			renderTo : Ext.getBody(),
			width: width,
	        height: height,
	        title: '任务管理',
	        layout: 'fit',
	        frame: true,
	        selModel:selModel,
	        tbar: [
	            {
	            	itemId: 'js_mgmt_add_row',
	            	text: '增加Job',
	            	//iconCls: 'employee-add',
		            disabled: false,
		            handler : function() {
		                rowEditing.cancelEdit();

		                // Create a model instance
		                var r = Ext.create('mgmtModel', {
		                	'jobId': '',
		                	'jobDescription':'',
		                	'status':'已停止',
		                	'cmdStartLocation':'',
		                	'cmdStopLoacation':'',
		                	'checkInterval':''
		                });

		                store.insert(0, r);
		                rowEditing.startEdit(0, 0);
		            }
	            },
	            {
		            itemId: 'js_mgmt_delete_row',
		            text: '删除Job',
		            //iconCls: 'employee-remove',
		            handler: function() {
		                var sm = dubbocenter.mgmt.grid.getSelectionModel();
		                rowEditing.cancelEdit();
		                
		                var records = sm.getSelection();
		                if(records.length <= 0){
		                	dubbocenter.warningResult('操作提示','请选择要删除的任务');
		                }else{
		                	var jobIds = new Array();
		                	for(var i=0; i < records.length; i++){
		                		var record = records[i];
		                		jobIds.push(record.data.jobId);
		                	}
		                	var progress = dubbocenter.progressBar('正在修改，请稍后...');
		        			progress.show();
		                	Ext.Ajax.request({
			        			url : ctx + '/mgmt/delete',
			        			method : 'post', 
			        			params : {
			        				jobIds : jobIds
			        			},
			        			success : function(response){
			        				progress.hide();
			        				var result = Ext.decode(response.responseText);
			        				if(result.success){
			        					dubbocenter.warningResult('操作提示','删除成功');
			        				}else{
			        					dubbocenter.warningResult('操作提示',result.errorMessage);
			        				}
			        				dubbocenter.mgmt.grid.store.reload();
			        			},
			        			failure : function(response, opts){
			        				progress.hide();
			        				dubbocenter.warningResult('操作提示','网络异常，保存失败');
			        			}
			        		});
		                }
		            },
		            disabled: false
		        },
		        {
		            itemId: 'js_mgmt_start_row',
		            text: '启用Job',
		            //iconCls: 'employee-remove',
		            handler: function() {
		                var sm = dubbocenter.mgmt.grid.getSelectionModel();
		                rowEditing.cancelEdit();
		                
		                var records = sm.getSelection();
		                if(records.length <= 0){
		                	dubbocenter.warningResult('操作提示','请选择要启动的任务');
		                }else if(records.length > 1){
		                	dubbocenter.warningResult('操作提示','请逐个启动');
		                }else{
		                	var jobId = records[0].data.jobId;
		                	var progress = dubbocenter.progressBar('正在修改，请稍后...');
		        			progress.show();
		                	Ext.Ajax.request({
			        			url : ctx + '/mgmt/start',
			        			method : 'post', 
			        			params : {
			        				jobId : jobId
			        			},
			        			success : function(response){
			        				progress.hide();
			        				var result = Ext.decode(response.responseText);
			        				if(result.success){
			        					dubbocenter.warningResult('操作提示','启动成功');
			        				}else{
			        					dubbocenter.warningResult('操作提示',result.errorMessage);
			        				}
			        				dubbocenter.mgmt.grid.store.reload();
			        			},
			        			failure : function(response, opts){
			        				progress.hide();
			        				dubbocenter.warningResult('操作提示','网络异常，保存失败');
			        			}
			        		});
		                }
		            },
		            disabled: false
		        },
		        {
		            itemId: 'js_mgmt_stop_row',
		            text: '停止Job',
		            //iconCls: 'employee-remove',
		            handler: function() {
		                var sm = dubbocenter.mgmt.grid.getSelectionModel();
		                rowEditing.cancelEdit();
		                
		                var records = sm.getSelection();
		                if(records.length <= 0){
		                	dubbocenter.warningResult('操作提示','请选择要停止的数据');
		                }else if(records.length > 1){
		                	dubbocenter.warningResult('操作提示','请逐个启动');
		                }else{
		                	var jobId = records[0].data.jobId;
		                	var progress = dubbocenter.progressBar('正在修改，请稍后...');
		        			progress.show();
		                	Ext.Ajax.request({
			        			url : ctx + '/mgmt/stop',
			        			method : 'post', 
			        			params : {
			        				jobId : jobId
			        			},
			        			success : function(response){
			        				progress.hide();
			        				var result = Ext.decode(response.responseText);
			        				if(result.success){
			        					dubbocenter.warningResult('操作提示','停止成功');
			        				}else{
			        					dubbocenter.warningResult('操作提示',result.errorMessage);
			        				}
			        				dubbocenter.mgmt.grid.store.reload();
			        			},
			        			failure : function(response, opts){
			        				progress.hide();
			        				dubbocenter.warningResult('操作提示','网络异常，保存失败');
			        			}
			        		});
		                }
		            },
		            disabled: false
		        },
		        {
		            itemId: 'js_mgmt_refresh_row',
		            text: '刷新',
		            //iconCls: 'employee-remove',
		            handler: function() {
		            	dubbocenter.mgmt.grid.store.reload();
		            },
		            disabled: false
		        }
	        ],
	        plugins: [rowEditing]
		});
	}
};

$(function(){
	Ext.onReady(function () {
		Ext.QuickTips.init();
		dubbocenter.mgmt.init();
		
		
	});
	
	Ext.EventManager.onWindowResize(function () {
		var height = $(window).height();
		var width = $(window).width();
		dubbocenter.mgmt.grid.setSize(width, height);
    });
});