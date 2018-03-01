<c:if test="${msgInfo!=null}">
	<div class="alert alert-info alert-dismissable screenBody">
	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	<strong>${msgInfoTitle} </strong>${msgInfo}
	</div>
</c:if>

<c:if test="${msgSuccess!=null}">
	<div class="alert alert-success alert-dismissable screenBody">
	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	<strong>${msgSuccessTitle} </strong>${msgSuccess}
	</div>
</c:if>

<c:if test="${msgWarning!=null}">
	<div class="alert alert-warning alert-dismissable screenBody">
	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	<strong>${msgWarningTitle} </strong>${msgWarning}
	</div>
</c:if>

<c:if test="${msgDanger!=null}">
	<div class="alert alert-danger alert-dismissable screenBody">
	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	<strong>${msgDangerTitle} </strong>${msgDanger}
	</div>
</c:if>
