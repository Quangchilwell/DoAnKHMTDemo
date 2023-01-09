
document.addEventListener('DOMContentLoaded', function(){
	const formSoftDelete = document.forms["form-soft-delete"]
 	const formRestore = document.forms['restore-form']
 	const formForceDelete = document.forms['force-delete-form']
 	const formAccept = document.forms['accept-form']
 	
 	const btnSelectAll = document.querySelector('.select-all')
 	const selectItems = document.querySelectorAll('.select-item')
 	const btnSelectAllSubmit = document.querySelector('.check-all-submit-btn') 
 	const selectAction = document.querySelector('.select-action')

 	const btnSoftDeletes = document.querySelectorAll('.btn-soft-delete')
 	const btnDelete = document.querySelector('.btn-delete')
 	const btnRestores = document.querySelectorAll('.btn-restore')
 	const btnForceDeletes = document.querySelectorAll('.btn-force-delete')
 	const btnAccepts = document.querySelectorAll('.btn-accept')

 	// Xu li nut chon tat ca
 	if(btnSelectAll){
	 	btnSelectAll.onchange = function()
	 	{
			if(btnSelectAll.checked){
				selectItems.forEach(function(item){
					item.checked = true
				})
			}
			else{
				selectItems.forEach(function(item){
					item.checked = false
				})
			}	
		}
	}
	// Xu li nut chon cua tung ban ghi
	if(selectItems){
		selectItems.forEach(function(item){
			item.onchange = function(){
				var itemsIsChecked = document.querySelectorAll('input[name="idRecords[]"]:checked');
				if(itemsIsChecked.length === selectItems.length){
					btnSelectAll.checked = true
				}
				else{
					btnSelectAll.checked = false
				}
			}
		})		
	}
	
	// Nut thuc hien dang bi vo hieu hoa
	if(selectAction){
		selectAction.onchange = function(){
			renderBtnSelectAllSubmit()		
		}		
	}
 	
 	// Xoa mem cac ban ghi
 	if(formSoftDelete){
	 	btnSoftDeletes.forEach(function(btnSoftDelete){
			btnSoftDelete.onclick = function(e)
			{
				 e.preventDefault()
				 var recordID = btnSoftDelete.getAttribute('dataid')
				 btnDelete.onclick = function(){
					 formSoftDelete.action = formSoftDelete.action + recordID
					 //console.log(formSoftDelete.action)
					 formSoftDelete.submit()
				}
			}
		})	
	}
 	
 	// Khoi phuc cac ban ghi
	btnRestores.forEach(function(btnRestore){
		btnRestore.onclick = function(e)
		{
			 e.preventDefault()
			 var recordID = btnRestore.getAttribute('dataid')
			 formRestore.action = formRestore.action + recordID
			formRestore.submit()
		}
	})
	
	// Xoa vĩnh vien cac ban ghi
	btnForceDeletes.forEach(function(btnForceDelete){
		btnForceDelete.onclick = function(e)
		{
			 e.preventDefault()
			 var recordID = btnForceDelete.getAttribute('dataid')
			 btnDelete.onclick = function(){
				 formForceDelete.action = formForceDelete.action + recordID
				 //console.log(formForceDelete.action)
				 formForceDelete.submit()				
			}
		}
	})
	
	if(btnAccepts){
		btnAccepts.forEach(function(btnAccept){
			btnAccept.onclick = function(e)
			{
				e.preventDefault()
				var recordID = btnAccept.getAttribute('dataid')
				formAccept.action = formAccept.action + recordID
				console.log(formAccept.action)
				formAccept.submit()				
			}
		})		
	}
	
	function renderBtnSelectAllSubmit(){
		if(selectAction.value){
			btnSelectAllSubmit.removeAttribute('disabled')
		}
		else{
			btnSelectAllSubmit.setAttribute('disabled', 'disabled')
		}
	}
})

 