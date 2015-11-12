dubbocenter.mgmt.store = {
		initStore : function(){
			var mgmtModel = Ext.define('mgmtModel', {
		        extend: 'Ext.data.Model',
		        fields: [
		            'jobId',
		            'jobDescription',
		            'status',
		            'cmdStartLocation',
		            'cmdStopLoacation',
		            'checkInterval'
		        ]
			});
			
			var mgmtSotre = Ext.create('Ext.data.Store',{
				model: mgmtModel,
				proxy : {
					type: 'ajax',
					url : ctx + '/mgmt/listStore',
					method : 'get',
					reader: {
			             type: 'json',
			             root : ''
			         }
				},
				autoLoad : true
			});
			return mgmtSotre;
		},
		
		initIntervalStore : function(){
			var intervalSotre = Ext.create('Ext.data.Store',{
				fields: ['value', 'label'],
				data : [
				    {'value':'3600000', 'label':'1小时'},
				    {'value':'86400000', 'label':'1天'}
				]
			});
			return intervalSotre;
		}
}