/**
 * 
 */
const formDeleteRoomBusy = document.forms['force-delete-roomBusy-in-invoice']
const btnForceDeleteRBs = document.querySelectorAll('.btn-force-delete-RB')

if(btnForceDeleteRBs){
	btnForceDeleteRBs.forEach(function(btn){
	   btn.onclick = function(e){
	        e.preventDefault()
	        var recordID = btn.getAttribute('dataid')
	        formDeleteRoomBusy.action = formDeleteRoomBusy.action + recordID
	        formDeleteRoomBusy.submit()
	    }
	})
}

