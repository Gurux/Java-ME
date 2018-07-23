package gurux.dlms;

import gurux.dlms.enums.AccessServiceCommandType;
import gurux.dlms.enums.Command;
import gurux.dlms.enums.DataType;
import gurux.dlms.enums.ErrorCode;

final class TranslatorStandardTags {
	/**
	 * Constructor.
	 */
	private TranslatorStandardTags() {

	}

	/**
	 * Get general tags.
	 * 
	 * @param type
	 * @param list
	 */
	static void getGeneralTags(final TranslatorOutputType type,
			final java.util.Hashtable list) {
		GXDLMSTranslator.addTag(list, Command.SNRM, "Snrm");
		GXDLMSTranslator.addTag(list, Command.UA, "Ua");
		GXDLMSTranslator.addTag(list, Command.AARQ, "x:aarq");
		GXDLMSTranslator.addTag(list, Command.AARE, "x:aare");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.APPLICATION_CONTEXT_NAME,
				"x:application-context-name");
		GXDLMSTranslator.addTag(list, Command.INITIATE_RESPONSE,
				"x:initiateResponse");
		GXDLMSTranslator.addTag(list, Command.INITIATE_REQUEST,
				"x:initiateRequest");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.NEGOTIATED_QUALITY_OF_SERVICE,
				"x:negotiated-quality-of-service");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.PROPOSED_QUALITY_OF_SERVICE,
				"x:proposed-quality-of-service");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.PROPOSED_DLMS_VERSION_NUMBER,
				"x:proposed-dlms-version-number");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.PROPOSED_MAX_PDU_SIZE,
				"x:client-max-receive-pdu-size");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.PROPOSED_CONFORMANCE,
				"x:proposed-conformance");
		GXDLMSTranslator.addTag(list, TranslatorGeneralTags.VAA_NAME,
				"x:vaa-name");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.NEGOTIATED_CONFORMANCE,
				"x:negotiated-conformance");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.NEGOTIATED_DLMS_VERSION_NUMBER,
				"x:negotiated-dlms-version-number");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.NEGOTIATED_MAX_PDU_SIZE,
				"x:server-max-receive-pdu-size");
		GXDLMSTranslator.addTag(list, TranslatorGeneralTags.CONFORMANCE_BIT,
				"ConformanceBit");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.SENDER_ACSE_REQUIREMENTS,
				"x:sender-acse-requirements");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.RESPONDER_ACSE_REQUIREMENT,
				"x:responder-acse-requirements");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.RESPONDING_MECHANISM_NAME,
				"x:mechanism-name");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.CALLING_MECHANISM_NAME,
				"x:mechanism-name");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.CALLING_AUTHENTICATION,
				"x:calling-authentication-value");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.RESPONDING_AUTHENTICATION,
				"x:responding-authentication-value");
		GXDLMSTranslator.addTag(list, Command.DISCONNECT_REQUEST,
				"ReleaseRequest");
		GXDLMSTranslator.addTag(list, Command.DISCONNECT_RESPONSE,
				"ReleaseResponse");
		GXDLMSTranslator.addTag(list, Command.DISC, "Disc");
		GXDLMSTranslator.addTag(list, TranslatorGeneralTags.ASSOCIATION_RESULT,
				"x:result");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.RESULT_SOURCE_DIAGNOSTIC,
				"x:result-source-diagnostic");
		GXDLMSTranslator.addTag(list, TranslatorGeneralTags.ACSE_SERVICE_USER,
				"x:acse-service-user");
		GXDLMSTranslator.addTag(list, TranslatorGeneralTags.CALLING_AP_TITLE,
				"CallingAPTitle");
		GXDLMSTranslator.addTag(list, TranslatorGeneralTags.RESPONDING_AP_TITLE,
				"RespondingAPTitle");
		GXDLMSTranslator.addTag(list, TranslatorGeneralTags.CHAR_STRING,
				"x:charstring");
		GXDLMSTranslator.addTag(list, TranslatorGeneralTags.DEDICATED_KEY,
				"x:dedicated-key");
		GXDLMSTranslator.addTag(list, TranslatorGeneralTags.RESPONSE_ALLOWED,
				"x:response-allowed");
		GXDLMSTranslator.addTag(list, TranslatorGeneralTags.USER_INFORMATION,
				"x:user-information");
	}

	/**
	 * Get SN tags.
	 * 
	 * @param type
	 * @param list
	 */
	static void getSnTags(final TranslatorOutputType type,
			final java.util.Hashtable list) {
		GXDLMSTranslator.addTag(list, Command.READ_REQUEST, "x:readRequest");
		GXDLMSTranslator.addTag(list, Command.WRITE_REQUEST, "x:writeRequest");
		GXDLMSTranslator.addTag(list, Command.WRITE_RESPONSE,
				"x:writeResponse");
		GXDLMSTranslator.addTag(list,
				Command.WRITE_REQUEST << 8 | SingleReadResponse.DATA, "x:Data");
		GXDLMSTranslator.addTag(list,
				Command.READ_REQUEST << 8
						| VariableAccessSpecification.VARIABLE_NAME,
				"x:variable-name");
		GXDLMSTranslator.addTag(list,
				Command.READ_REQUEST << 8
						| VariableAccessSpecification.PARAMETERISED_ACCESS,
				"x:parameterized-access");
		GXDLMSTranslator.addTag(list,
				Command.READ_REQUEST << 8
						| VariableAccessSpecification.BLOCK_NUMBER_ACCESS,
				"BlockNumberAccess");
		GXDLMSTranslator.addTag(list,
				Command.WRITE_REQUEST << 8
						| VariableAccessSpecification.VARIABLE_NAME,
				"x:variable-name");

		GXDLMSTranslator.addTag(list, Command.READ_RESPONSE, "x:readResponse");
		GXDLMSTranslator.addTag(list,
				Command.READ_RESPONSE << 8
						| SingleReadResponse.DATA_BLOCK_RESULT,
				"DataBlockResult");
		GXDLMSTranslator.addTag(list,
				Command.READ_RESPONSE << 8 | SingleReadResponse.DATA, "x:data");
		GXDLMSTranslator.addTag(list,
				Command.WRITE_RESPONSE << 8 | SingleReadResponse.DATA,
				"x:data");
		GXDLMSTranslator.addTag(list,
				Command.READ_RESPONSE << 8
						| SingleReadResponse.DATA_ACCESS_ERROR,
				"x:data-access-error");
	}

	/**
	 * Get LN tags.
	 * 
	 * @param type
	 * @param list
	 */
	static void getLnTags(final TranslatorOutputType type,
			final java.util.Hashtable list) {
		GXDLMSTranslator.addTag(list, Command.GET_REQUEST, "x:get-request");
		GXDLMSTranslator.addTag(list,
				Command.GET_REQUEST << 8 | GetCommandType.NORMAL,
				"x:get-request-normal");
		GXDLMSTranslator.addTag(list,
				Command.GET_REQUEST << 8 | GetCommandType.NEXT_DATA_BLOCK,
				"x:get-request-next");
		GXDLMSTranslator.addTag(list,
				Command.GET_REQUEST << 8 | GetCommandType.WITH_LIST,
				"x:get-request-with-list");
		GXDLMSTranslator.addTag(list, Command.SET_REQUEST, "x:set-request");
		GXDLMSTranslator.addTag(list,
				Command.SET_REQUEST << 8 | SetRequestType.NORMAL,
				"x:set-request-normal");
		GXDLMSTranslator.addTag(list,
				Command.SET_REQUEST << 8 | SetRequestType.FIRST_DATA_BLOCK,
				"x:set-request-first-data-block");
		GXDLMSTranslator.addTag(list,
				Command.SET_REQUEST << 8 | SetRequestType.WITH_DATA_BLOCK,
				"x:set-request-with-data-block");
		GXDLMSTranslator.addTag(list,
				Command.SET_REQUEST << 8 | SetRequestType.WITH_LIST,
				"x:set-request-with-list");
		GXDLMSTranslator.addTag(list, Command.METHOD_REQUEST,
				"x:action-request");
		GXDLMSTranslator.addTag(list,
				Command.METHOD_REQUEST << 8 | ActionRequestType.NORMAL,
				"x:action-request-normal");
		GXDLMSTranslator.addTag(list,
				Command.METHOD_REQUEST << 8 | ActionRequestType.NEXT_BLOCK,
				"ActionRequestForNextDataBlock");
		GXDLMSTranslator.addTag(list,
				Command.METHOD_REQUEST << 8 | ActionRequestType.WITH_LIST,
				"x:action-request-with-list");
		GXDLMSTranslator.addTag(list, Command.METHOD_RESPONSE,
				"x:action-response");
		GXDLMSTranslator.addTag(list,
				Command.METHOD_RESPONSE << 8 | ActionResponseType.NORMAL,
				"x:action-response-normal");
		GXDLMSTranslator.addTag(list,
				Command.METHOD_RESPONSE << 8
						| ActionResponseType.WITH_FIRST_BLOCK,
				"x:action-response-with-first-block");
		GXDLMSTranslator.addTag(list,
				Command.METHOD_RESPONSE << 8 | ActionResponseType.WITH_LIST,
				"x:action-response-with-list");
		GXDLMSTranslator.addTag(list, TranslatorTags.SINGLE_RESPONSE,
				"x:single-response");

		GXDLMSTranslator.addTag(list, (int) Command.DATA_NOTIFICATION,
				"x:data-notification");
		GXDLMSTranslator.addTag(list, Command.GET_RESPONSE, "x:get-response");
		GXDLMSTranslator.addTag(list,
				Command.GET_RESPONSE << 8 | GetCommandType.NORMAL,
				"x:get-response-normal");
		GXDLMSTranslator.addTag(list,
				Command.GET_RESPONSE << 8 | GetCommandType.NEXT_DATA_BLOCK,
				"x:get-response-with-data-block");
		GXDLMSTranslator.addTag(list,
				Command.GET_RESPONSE << 8 | GetCommandType.WITH_LIST,
				"x:get-response-with-list");
		GXDLMSTranslator.addTag(list, Command.SET_RESPONSE, "x:set-response");
		GXDLMSTranslator.addTag(list,
				Command.SET_RESPONSE << 8 | SetResponseType.NORMAL,
				"x:set-response-normal");
		GXDLMSTranslator.addTag(list,
				Command.SET_RESPONSE << 8 | SetResponseType.DATA_BLOCK,
				"x:set-response-data-block");
		GXDLMSTranslator.addTag(list,
				Command.SET_RESPONSE << 8 | SetResponseType.LAST_DATA_BLOCK,
				"x:set-response-with-last-data-block");
		GXDLMSTranslator.addTag(list,
				Command.SET_RESPONSE << 8 | SetResponseType.WITH_LIST,
				"x:set-response-with-list");

		GXDLMSTranslator.addTag(list, Command.ACCESS_REQUEST,
				"x:access-request");
		GXDLMSTranslator.addTag(list,
				(Command.ACCESS_REQUEST) << 8 | AccessServiceCommandType.GET,
				"x:access-request-get");
		GXDLMSTranslator.addTag(list,
				(Command.ACCESS_REQUEST) << 8 | AccessServiceCommandType.SET,
				"x:access-request-set");
		GXDLMSTranslator.addTag(list,
				(Command.ACCESS_REQUEST) << 8 | AccessServiceCommandType.ACTION,
				"x:access-request-action");
		GXDLMSTranslator.addTag(list, Command.ACCESS_RESPONSE,
				"x:access-response");
		GXDLMSTranslator.addTag(list,
				(Command.ACCESS_RESPONSE) << 8 | AccessServiceCommandType.GET,
				"x:access-response-get");
		GXDLMSTranslator.addTag(list,
				(Command.ACCESS_RESPONSE) << 8 | AccessServiceCommandType.SET,
				"x:access-response-set");
		GXDLMSTranslator.addTag(list,
				(Command.ACCESS_RESPONSE) << 8
						| AccessServiceCommandType.ACTION,
				"x:access-response-action");

		GXDLMSTranslator.addTag(list, TranslatorTags.ACCESS_REQUEST_BODY,
				"x:access-request-body");
		GXDLMSTranslator.addTag(list,
				TranslatorTags.LIST_OF_ACCESS_REQUEST_SPECIFICATION,
				"x:access-request-specification");
		GXDLMSTranslator.addTag(list,
				TranslatorTags.ACCESS_REQUEST_SPECIFICATION,
				"x:Access-Request-Specification");
		GXDLMSTranslator.addTag(list,
				TranslatorTags.ACCESS_REQUEST_LIST_OF_DATA,
				"x:access-request-list-of-data");

		GXDLMSTranslator.addTag(list, TranslatorTags.ACCESS_RESPONSE_BODY,
				"x:access-response-body");
		GXDLMSTranslator.addTag(list,
				TranslatorTags.LIST_OF_ACCESS_RESPONSE_SPECIFICATION,
				"x:access-response-specification");
		GXDLMSTranslator.addTag(list,
				TranslatorTags.ACCESS_RESPONSE_SPECIFICATION,
				"x:Access-Response-Specification");
		GXDLMSTranslator.addTag(list,
				TranslatorTags.ACCESS_RESPONSE_LIST_OF_DATA,
				"x:access-response-list-of-data");
	}

	/**
	 * Get glo tags.
	 * 
	 * @param type
	 * @param list
	 */
	static void getGloTags(final TranslatorOutputType type,
			final java.util.Hashtable list) {
		GXDLMSTranslator.addTag(list, Command.GLO_INITIATE_REQUEST,
				"glo_InitiateRequest");
		GXDLMSTranslator.addTag(list, Command.GLO_INITIATE_RESPONSE,
				"glo_InitiateResponse");
		GXDLMSTranslator.addTag(list, Command.GLO_GET_REQUEST,
				"glo_GetRequest");
		GXDLMSTranslator.addTag(list, Command.GLO_GET_RESPONSE,
				"glo_GetResponse");
		GXDLMSTranslator.addTag(list, Command.GLO_SET_REQUEST,
				"glo_SetRequest");
		GXDLMSTranslator.addTag(list, Command.GLO_SET_RESPONSE,
				"glo_SetResponse");
		GXDLMSTranslator.addTag(list, Command.GLO_METHOD_REQUEST,
				"glo_ActionRequest");
		GXDLMSTranslator.addTag(list, Command.GLO_METHOD_RESPONSE,
				"glo_ActionResponse");
		GXDLMSTranslator.addTag(list, Command.GLO_READ_REQUEST,
				"glo_ReadRequest");
		GXDLMSTranslator.addTag(list, Command.GLO_READ_RESPONSE,
				"glo_ReadResponse");
		GXDLMSTranslator.addTag(list, Command.GLO_WRITE_REQUEST,
				"glo_WriteRequest");
		GXDLMSTranslator.addTag(list, Command.GLO_WRITE_RESPONSE,
				"glo_WriteResponse");
	}

	/**
	 * Get translator tags.
	 * 
	 * @param type
	 * @param list
	 */
	static void getTranslatorTags(final TranslatorOutputType type,
			final java.util.Hashtable list) {
		GXDLMSTranslator.addTag(list, TranslatorTags.WRAPPER, "Wrapper");
		GXDLMSTranslator.addTag(list, TranslatorTags.HDLC, "Hdlc");
		GXDLMSTranslator.addTag(list, TranslatorTags.PDU_DLMS, "x:xDLMS-APDU");
		GXDLMSTranslator.addTag(list, TranslatorTags.PDU_CSE, "x:aCSE-APDU");
		GXDLMSTranslator.addTag(list, TranslatorTags.TARGET_ADDRESS,
				"TargetAddress");
		GXDLMSTranslator.addTag(list, TranslatorTags.SOURCE_ADDRESS,
				"SourceAddress");
		GXDLMSTranslator.addTag(list,
				TranslatorTags.LIST_OF_VARIABLE_ACCESS_SPECIFICATION,
				"x:variable-access-specification");
		GXDLMSTranslator.addTag(list, TranslatorTags.LIST_OF_DATA,
				"x:list-of-data");
		GXDLMSTranslator.addTag(list, TranslatorTags.SUCCESS, "Success");
		GXDLMSTranslator.addTag(list, TranslatorTags.DATA_ACCESS_ERROR,
				"x:data-access-result");
		GXDLMSTranslator.addTag(list, TranslatorTags.ATTRIBUTE_DESCRIPTOR,
				"x:cosem-attribute-descriptor");
		GXDLMSTranslator.addTag(list, TranslatorTags.CLASS_ID, "x:class-id");
		GXDLMSTranslator.addTag(list, TranslatorTags.INSTANCE_ID,
				"x:instance-id");
		GXDLMSTranslator.addTag(list, TranslatorTags.ATTRIBUTE_ID,
				"x:attribute-id");
		GXDLMSTranslator.addTag(list,
				TranslatorTags.METHOD_INVOCATION_PARAMETERS,
				"x:method-invocation-parameters");
		GXDLMSTranslator.addTag(list, TranslatorTags.SELECTOR, "x:selector");
		GXDLMSTranslator.addTag(list, TranslatorTags.PARAMETER, "x:parameter");
		GXDLMSTranslator.addTag(list, TranslatorTags.LAST_BLOCK, "LastBlock");
		GXDLMSTranslator.addTag(list, TranslatorTags.BLOCK_NUMBER,
				"x:block-number");
		GXDLMSTranslator.addTag(list, TranslatorTags.RAW_DATA, "RawData");
		GXDLMSTranslator.addTag(list, TranslatorTags.METHOD_DESCRIPTOR,
				"x:cosem-method-descriptor");
		GXDLMSTranslator.addTag(list, TranslatorTags.METHOD_ID, "x:method-id");
		GXDLMSTranslator.addTag(list, TranslatorTags.RESULT, "x:result");
		GXDLMSTranslator.addTag(list, TranslatorTags.RETURN_PARAMETERS,
				"x:return-parameters");
		GXDLMSTranslator.addTag(list, TranslatorTags.ACCESS_SELECTION,
				"x:access-selection");
		GXDLMSTranslator.addTag(list, TranslatorTags.VALUE, "x:value");
		GXDLMSTranslator.addTag(list, TranslatorTags.ACCESS_SELECTOR,
				"x:access-selector");
		GXDLMSTranslator.addTag(list, TranslatorTags.ACCESS_PARAMETERS,
				"x:access-parameters");
		GXDLMSTranslator.addTag(list, TranslatorTags.ATTRIBUTE_DESCRIPTOR_LIST,
				"AttributeDescriptorList");
		GXDLMSTranslator.addTag(list,
				TranslatorTags.ATTRIBUTE_DESCRIPTOR_WITH_SELECTION,
				"AttributeDescriptorWithSelection");
		GXDLMSTranslator.addTag(list, TranslatorTags.READ_DATA_BLOCK_ACCESS,
				"ReadDataBlockAccess");
		GXDLMSTranslator.addTag(list, TranslatorTags.WRITE_DATA_BLOCK_ACCESS,
				"WriteDataBlockAccess");
		GXDLMSTranslator.addTag(list, TranslatorTags.DATA, "x:data");
		GXDLMSTranslator.addTag(list, TranslatorTags.INVOKE_ID,
				"x:invoke-id-and-priority");
		GXDLMSTranslator.addTag(list, TranslatorTags.LONG_INVOKE_ID,
				"x:long-invoke-id-and-priority");
		GXDLMSTranslator.addTag(list, TranslatorTags.DATE_TIME, "x:date-time");
		GXDLMSTranslator.addTag(list, TranslatorTags.REASON, "Reason");
		GXDLMSTranslator.addTag(list,
				TranslatorTags.VARIABLE_ACCESS_SPECIFICATION,
				"x:Variable-Access-Specification");
		GXDLMSTranslator.addTag(list, TranslatorTags.CHOICE, "x:CHOICE");
		GXDLMSTranslator.addTag(list, TranslatorTags.NOTIFICATION_BODY,
				"x:notification-body");
		GXDLMSTranslator.addTag(list, TranslatorTags.DATA_VALUE,
				"x:data-value");

	}

	static void getDataTypeTags(final java.util.Hashtable list) {
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.NONE.getValue(),
				"x:null-data");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.ARRAY.getValue(), "x:array");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.BCD.getValue(), "x:bcd");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.BITSTRING.getValue(),
				"x:bit-string");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.BOOLEAN.getValue(),
				"x:boolean");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.COMPACT_ARRAY.getValue(),
				"x:compact-array");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.DATE.getValue(), "x:date");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.DATETIME.getValue(),
				"x:date-time");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.ENUM.getValue(), "x:enum");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.FLOAT32.getValue(),
				"x:float32");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.FLOAT64.getValue(),
				"x:float64,");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.INT16.getValue(), "x:long");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.INT32.getValue(),
				"x:double-long");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.INT64.getValue(),
				"x:long64");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.INT8.getValue(),
				"x:integer");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.OCTET_STRING.getValue(),
				"x:octet-string");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.STRING.getValue(),
				"x:visible-string");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.STRING_UTF8.getValue(),
				"x:utf8-string");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.STRUCTURE.getValue(),
				"x:structure");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.TIME.getValue(), "x:time");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.UINT16.getValue(),
				"x:long-unsigned");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.UINT32.getValue(),
				"x:double-long-unsigned");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.UINT64.getValue(),
				"x:long64-unsigned");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.UINT8.getValue(),
				"x:unsigned");
	}

	static String errorCodeToString(final ErrorCode value) {
		String str;
		switch (value.getValue()) {
		case 13:// ErrorCode.ACCESS_VIOLATED:
			str = "scope-of-access-violated";
			break;
		case 19:// ErrorCode.DATA_BLOCK_NUMBER_INVALID:
			str = "data-block-number-invalid";
			break;
		case 14:// ErrorCode.DATA_BLOCK_UNAVAILABLE:
			str = "data-block-unavailable";
			break;
		case 1:// ErrorCode.HARDWARE_FAULT:
			str = "hardware-fault";
			break;
		case 9:// ErrorCode.INCONSISTENT_CLASS:
			str = "object-class-inconsistent";
			break;
		case 15:// ErrorCode.LONG_GET_OR_READ_ABORTED:
			str = "long-get-aborted";
			break;
		case 17:// ErrorCode.LONG_SET_OR_WRITE_ABORTED:
			str = "long-set-aborted";
			break;
		case 16:// ErrorCode.NO_LONG_GET_OR_READ_IN_PROGRESS:
			str = "no-long-get-in-progress";
			break;
		case 18:// ErrorCode.NO_LONG_SET_OR_WRITE_IN_PROGRESS:
			str = "no-long-set-in-progress";
			break;
		case 0:// ErrorCode.OK:
			str = "success";
			break;
		case 250:// ErrorCode.OTHER_REASON:
			str = "other-reason";
			break;
		case 3:// ErrorCode.READ_WRITE_DENIED:
			str = "read-write-denied";
			break;
		case 2:// ErrorCode.TEMPORARY_FAILURE:
			str = "temporary-failure";
			break;
		case 11:// ErrorCode.UNAVAILABLE_OBJECT:
			str = "object-unavailable";
			break;
		case 4:// ErrorCode.UNDEFINED_OBJECT:
			str = "object-undefined";
			break;
		case 12:// ErrorCode.UNMATCHED_TYPE:
			str = "type-unmatched";
			break;
		default:
			throw new IllegalArgumentException(
					"Error code: " + String.valueOf(value));
		}
		return str;
	}

	static ErrorCode valueOfErrorCode(final String value) {
		ErrorCode v;
		if ("scope-of-access-violated".equalsIgnoreCase(value)) {
			v = ErrorCode.ACCESS_VIOLATED;
		} else if ("data-block-number-invalid".equalsIgnoreCase(value)) {
			v = ErrorCode.DATA_BLOCK_NUMBER_INVALID;
		} else if ("data-block-unavailable".equalsIgnoreCase(value)) {
			v = ErrorCode.DATA_BLOCK_UNAVAILABLE;
		} else if ("hardware-fault".equalsIgnoreCase(value)) {
			v = ErrorCode.HARDWARE_FAULT;
		} else if ("object-class-inconsistent".equalsIgnoreCase(value)) {
			v = ErrorCode.INCONSISTENT_CLASS;
		} else if ("long-get-aborted".equalsIgnoreCase(value)) {
			v = ErrorCode.LONG_GET_OR_READ_ABORTED;
		} else if ("long-set-aborted".equalsIgnoreCase(value)) {
			v = ErrorCode.LONG_SET_OR_WRITE_ABORTED;
		} else if ("no-long-get-in-progress".equalsIgnoreCase(value)) {
			v = ErrorCode.NO_LONG_GET_OR_READ_IN_PROGRESS;
		} else if ("no-long-set-in-progress".equalsIgnoreCase(value)) {
			v = ErrorCode.NO_LONG_SET_OR_WRITE_IN_PROGRESS;
		} else if ("success".equalsIgnoreCase(value)) {
			v = ErrorCode.OK;
		} else if ("other-reason".equalsIgnoreCase(value)) {
			v = ErrorCode.OTHER_REASON;
		} else if ("read-write-denied".equalsIgnoreCase(value)) {
			v = ErrorCode.READ_WRITE_DENIED;
		} else if ("temporary-failure".equalsIgnoreCase(value)) {
			v = ErrorCode.TEMPORARY_FAILURE;
		} else if ("object-unavailable".equalsIgnoreCase(value)) {
			v = ErrorCode.UNAVAILABLE_OBJECT;
		} else if ("object-undefined".equalsIgnoreCase(value)) {
			v = ErrorCode.UNDEFINED_OBJECT;
		} else if ("type-unmatched".equalsIgnoreCase(value)) {
			v = ErrorCode.UNMATCHED_TYPE;
		} else {
			throw new IllegalArgumentException("Error code: " + value);
		}
		return v;
	}
}
