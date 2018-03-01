<div id="updContact" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="contact">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="contact">Emergency Contact Form</h4>
			</div>
			<div class="modal-body">
				<div class="form-group row">
				<p><h4 class="text-center text-danger">This is the person we will contact in case of an emergency.</h4></p>
				</div>
				<div class="form-group row">
					<h6 class="text-danger col-md-offset-3"><ul id="contactErrorList"></ul></h6>
				</div>
				<div class="form-group row">
					<label class="control-label col-md-2 text-right" for="contactFirst">First
						Name</label>
					<div class="col-md-4">
						<input type="text" class="form-control" id="contactFirst"
							name="contactFirst"/>
					</div>
					<label class="control-label col-md-2 text-right" for="contactLast">Last
						Name</label>
					<div class="col-md-4">
						<input type="text" class="form-control" id="contactLast"
							name="contactLast" />
					</div>
				</div>
				</br>
				<div class="form-group row">
					<label class="control-label col-md-2 text-right" for="relationship">Relationship</label>
					<div class="col-md-4">
						<input type="text" class="form-control" id="relationship" name=""relationship""/>
					</div>
					<label class="control-label col-md-2 text-right" for="contactPhone">Phone</label>
					<div class="col-md-4">
						<input type="text" class="form-control" id="contactPhone" placeholder="xxx-xxx-xxxx" 
							name="contactPhone"/>
					</div>
				</div>
				</br>
				<div class="form-group row">
					<label class="control-label col-md-2 text-right" for="contactEmail">Email
						Address</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="contactEmail" name="contactEmail"/>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="updContactForm();">Save</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->