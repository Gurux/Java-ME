package gurux.dlms;

import java.util.Hashtable;

import gurux.dlms.enums.AccessServiceCommandType;
import gurux.dlms.enums.Command;
import gurux.dlms.enums.DataType;
import gurux.dlms.enums.ErrorCode;

final class TranslatorSimpleTags {
	/**
	 * Constructor.
	 */
	private TranslatorSimpleTags() {

	}

	/**
	 * Get general tags.
	 * 
	 * @param type
	 * @param list
	 */
	static void getGeneralTags(final TranslatorOutputType type,
			final Hashtable list) {
		GXDLMSTranslator.addTag(list, Command.SNRM, "Snrm");
		GXDLMSTranslator.addTag(list, Command.UA, "Ua");
		GXDLMSTranslator.addTag(list, Command.AARQ, "AssociationRequest");
		GXDLMSTranslator.addTag(list, Command.AARE, "AssociationResponse");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.APPLICATION_CONTEXT_NAME,
				"ApplicationContextName");
		GXDLMSTranslator.addTag(list, Command.INITIATE_RESPONSE,
				"InitiateResponse");
		GXDLMSTranslator.addTag(list, Command.INITIATE_REQUEST,
				"InitiateRequest");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.NEGOTIATED_QUALITY_OF_SERVICE,
				"NegotiatedQualityOfService");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.PROPOSED_QUALITY_OF_SERVICE,
				"ProposedQualityOfService");

		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.PROPOSED_DLMS_VERSION_NUMBER,
				"ProposedDlmsVersionNumber");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.PROPOSED_MAX_PDU_SIZE,
				"ProposedMaxPduSize");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.PROPOSED_CONFORMANCE,
				"ProposedConformance");
		GXDLMSTranslator.addTag(list, TranslatorGeneralTags.VAA_NAME,
				"VaaName");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.NEGOTIATED_CONFORMANCE,
				"NegotiatedConformance");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.NEGOTIATED_DLMS_VERSION_NUMBER,
				"NegotiatedDlmsVersionNumber");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.NEGOTIATED_MAX_PDU_SIZE,
				"NegotiatedMaxPduSize");
		GXDLMSTranslator.addTag(list, TranslatorGeneralTags.CONFORMANCE_BIT,
				"ConformanceBit");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.SENDER_ACSE_REQUIREMENTS,
				"SenderACSERequirements");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.RESPONDER_ACSE_REQUIREMENT,
				"ResponderACSERequirement");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.RESPONDING_MECHANISM_NAME,
				"MechanismName");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.CALLING_MECHANISM_NAME, "MechanismName");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.CALLING_AUTHENTICATION,
				"CallingAuthentication");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.RESPONDING_AUTHENTICATION,
				"RespondingAuthentication");
		GXDLMSTranslator.addTag(list, Command.DISCONNECT_REQUEST,
				"ReleaseRequest");
		GXDLMSTranslator.addTag(list, Command.DISCONNECT_RESPONSE,
				"ReleaseResponse");
		GXDLMSTranslator.addTag(list, Command.DISC, "Disc");
		GXDLMSTranslator.addTag(list, TranslatorGeneralTags.ASSOCIATION_RESULT,
				"AssociationResult");
		GXDLMSTranslator.addTag(list,
				TranslatorGeneralTags.RESULT_SOURCE_DIAGNOSTIC,
				"ResultSourceDiagnostic");
		GXDLMSTranslator.addTag(list, TranslatorGeneralTags.ACSE_SERVICE_USER,
				"ACSEServiceUser");
		GXDLMSTranslator.addTag(list, TranslatorGeneralTags.CALLING_AP_TITLE,
				"CallingAPTitle");
		GXDLMSTranslator.addTag(list, TranslatorGeneralTags.RESPONDING_AP_TITLE,
				"RespondingAPTitle");
		GXDLMSTranslator.addTag(list, TranslatorGeneralTags.DEDICATED_KEY,
				"DedicatedKey");
	}

	/**
	 * Get SN tags.
	 * 
	 * @param type
	 * @param list
	 */
	static void getSnTags(final TranslatorOutputType type,
			final Hashtable list) {
		GXDLMSTranslator.addTag(list, Command.READ_REQUEST, "ReadRequest");
		GXDLMSTranslator.addTag(list, Command.WRITE_REQUEST, "WriteRequest");
		GXDLMSTranslator.addTag(list,
				Command.WRITE_REQUEST << 8 | SingleReadResponse.DATA,
				"VariableName");
		GXDLMSTranslator.addTag(list, Command.WRITE_RESPONSE, "WriteResponse");
		GXDLMSTranslator.addTag(list,
				Command.READ_REQUEST << 8
						| VariableAccessSpecification.VARIABLE_NAME,
				"VariableName");
		GXDLMSTranslator.addTag(list,
				Command.READ_REQUEST << 8
						| VariableAccessSpecification.PARAMETERISED_ACCESS,
				"ParameterisedAccess");
		GXDLMSTranslator.addTag(list,
				Command.READ_REQUEST << 8
						| VariableAccessSpecification.BLOCK_NUMBER_ACCESS,
				"BlockNumberAccess");
		GXDLMSTranslator.addTag(list,
				Command.WRITE_REQUEST << 8
						| VariableAccessSpecification.VARIABLE_NAME,
				"VariableName");
		GXDLMSTranslator.addTag(list, Command.READ_RESPONSE, "ReadResponse");
		GXDLMSTranslator.addTag(list,
				Command.READ_RESPONSE << 8
						| SingleReadResponse.DATA_BLOCK_RESULT,
				"DataBlockResult");
		GXDLMSTranslator.addTag(list,
				Command.READ_RESPONSE << 8 | SingleReadResponse.DATA, "Data");
		GXDLMSTranslator.addTag(list,
				Command.READ_RESPONSE << 8
						| SingleReadResponse.DATA_ACCESS_ERROR,
				"DataAccessError");
	}

	/**
	 * Get LN tags.
	 * 
	 * @param type
	 * @param list
	 */
	static void getLnTags(final TranslatorOutputType type,
			final Hashtable list) {
		GXDLMSTranslator.addTag(list, Command.GET_REQUEST, "GetRequest");
		GXDLMSTranslator.addTag(list,
				Command.GET_REQUEST << 8 | GetCommandType.NORMAL,
				"GetRequestNormal");
		GXDLMSTranslator.addTag(list,
				Command.GET_REQUEST << 8 | GetCommandType.NEXT_DATA_BLOCK,
				"GetRequestForNextDataBlock");
		GXDLMSTranslator.addTag(list,
				Command.GET_REQUEST << 8 | GetCommandType.WITH_LIST,
				"GetRequestWithList");
		GXDLMSTranslator.addTag(list, Command.SET_REQUEST, "SetRequest");
		GXDLMSTranslator.addTag(list,
				Command.SET_REQUEST << 8 | SetRequestType.NORMAL,
				"SetRequestNormal");
		GXDLMSTranslator.addTag(list,
				Command.SET_REQUEST << 8 | SetRequestType.FIRST_DATA_BLOCK,
				"SetRequestFirstDataBlock");
		GXDLMSTranslator.addTag(list,
				Command.SET_REQUEST << 8 | SetRequestType.WITH_DATA_BLOCK,
				"SetRequestWithDataBlock");
		GXDLMSTranslator.addTag(list,
				Command.SET_REQUEST << 8 | SetRequestType.WITH_LIST,
				"SetRequestWithList");
		GXDLMSTranslator.addTag(list, Command.METHOD_REQUEST, "ActionRequest");
		GXDLMSTranslator.addTag(list,
				Command.METHOD_REQUEST << 8 | ActionRequestType.NORMAL,
				"ActionRequestNormal");
		GXDLMSTranslator.addTag(list,
				Command.METHOD_REQUEST << 8 | ActionRequestType.NEXT_BLOCK,
				"ActionRequestForNextDataBlock");
		GXDLMSTranslator.addTag(list,
				Command.METHOD_REQUEST << 8 | ActionRequestType.WITH_LIST,
				"ActionRequestWithList");
		GXDLMSTranslator.addTag(list, Command.METHOD_RESPONSE,
				"ActionResponse");
		GXDLMSTranslator.addTag(list,
				Command.METHOD_RESPONSE << 8 | ActionResponseType.NORMAL,
				"ActionResponseNormal");
		GXDLMSTranslator.addTag(list,
				Command.METHOD_RESPONSE << 8
						| ActionResponseType.WITH_FIRST_BLOCK,
				"ActionResponseWithFirstBlock");
		GXDLMSTranslator.addTag(list,
				Command.METHOD_RESPONSE << 8 | ActionResponseType.WITH_LIST,
				"ActionResponseWithList");
		GXDLMSTranslator.addTag(list, (int) Command.DATA_NOTIFICATION,
				"DataNotification");
		GXDLMSTranslator.addTag(list, Command.GET_RESPONSE, "GetResponse");
		GXDLMSTranslator.addTag(list,
				Command.GET_RESPONSE << 8 | GetCommandType.NORMAL,
				"GetResponseNormal");
		GXDLMSTranslator.addTag(list,
				Command.GET_RESPONSE << 8 | GetCommandType.NEXT_DATA_BLOCK,
				"GetResponsewithDataBlock");
		GXDLMSTranslator.addTag(list,
				Command.GET_RESPONSE << 8 | GetCommandType.WITH_LIST,
				"GetResponseWithList");
		GXDLMSTranslator.addTag(list, Command.SET_RESPONSE, "SetResponse");
		GXDLMSTranslator.addTag(list,
				Command.SET_RESPONSE << 8 | SetResponseType.NORMAL,
				"SetResponseNormal");
		GXDLMSTranslator.addTag(list,
				Command.SET_RESPONSE << 8 | SetResponseType.DATA_BLOCK,
				"SetResponseDataBlock");
		GXDLMSTranslator.addTag(list,
				Command.SET_RESPONSE << 8 | SetResponseType.LAST_DATA_BLOCK,
				"SetResponseWithLastDataBlock");
		GXDLMSTranslator.addTag(list,
				Command.SET_RESPONSE << 8 | SetResponseType.WITH_LIST,
				"SetResponseWithList");

		GXDLMSTranslator.addTag(list, Command.ACCESS_REQUEST, "AccessRequest");
		GXDLMSTranslator.addTag(list,
				(Command.ACCESS_REQUEST) << 8 | AccessServiceCommandType.GET,
				"AccessRequestGet");
		GXDLMSTranslator.addTag(list,
				(Command.ACCESS_REQUEST) << 8 | AccessServiceCommandType.SET,
				"AccessRequestSet");
		GXDLMSTranslator.addTag(list,
				(Command.ACCESS_REQUEST) << 8 | AccessServiceCommandType.ACTION,
				"AccessRequestAction");
		GXDLMSTranslator.addTag(list, Command.ACCESS_RESPONSE,
				"AccessResponse");
		GXDLMSTranslator.addTag(list,
				(Command.ACCESS_RESPONSE) << 8 | AccessServiceCommandType.GET,
				"AccessResponseGet");
		GXDLMSTranslator.addTag(list,
				(Command.ACCESS_RESPONSE) << 8 | AccessServiceCommandType.SET,
				"AccessResponseSet");
		GXDLMSTranslator.addTag(list,
				(Command.ACCESS_RESPONSE) << 8
						| AccessServiceCommandType.ACTION,
				"AccessResponseAction");
		GXDLMSTranslator.addTag(list, TranslatorTags.ACCESS_REQUEST_BODY,
				"AccessRequestBody");
		GXDLMSTranslator.addTag(list,
				TranslatorTags.LIST_OF_ACCESS_REQUEST_SPECIFICATION,
				"AccessRequestSpecification");
		GXDLMSTranslator.addTag(list,
				TranslatorTags.ACCESS_REQUEST_SPECIFICATION,
				"_AccessRequestSpecification");
		GXDLMSTranslator.addTag(list,
				TranslatorTags.ACCESS_REQUEST_LIST_OF_DATA,
				"AccessRequestListOfData");

		GXDLMSTranslator.addTag(list, TranslatorTags.ACCESS_RESPONSE_BODY,
				"AccessResponseBody");
		GXDLMSTranslator.addTag(list,
				TranslatorTags.LIST_OF_ACCESS_RESPONSE_SPECIFICATION,
				"AccessResponseSpecification");
		GXDLMSTranslator.addTag(list,
				TranslatorTags.ACCESS_RESPONSE_SPECIFICATION,
				"_AccessResponseSpecification");
		GXDLMSTranslator.addTag(list,
				TranslatorTags.ACCESS_RESPONSE_LIST_OF_DATA,
				"AccessResponseListOfData");
	}

	/**
	 * Get glo tags.
	 * 
	 * @param type
	 * @param list
	 */
	static void getGloTags(final TranslatorOutputType type,
			final Hashtable list) {
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
			final Hashtable list) {
		GXDLMSTranslator.addTag(list, TranslatorTags.WRAPPER, "Wrapper");
		GXDLMSTranslator.addTag(list, TranslatorTags.HDLC, "Hdlc");
		GXDLMSTranslator.addTag(list, TranslatorTags.PDU_DLMS, "Pdu");
		GXDLMSTranslator.addTag(list, TranslatorTags.TARGET_ADDRESS,
				"TargetAddress");
		GXDLMSTranslator.addTag(list, TranslatorTags.SOURCE_ADDRESS,
				"SourceAddress");
		GXDLMSTranslator.addTag(list,
				TranslatorTags.LIST_OF_VARIABLE_ACCESS_SPECIFICATION,
				"ListOfVariableAccessSpecification");
		GXDLMSTranslator.addTag(list, TranslatorTags.LIST_OF_DATA,
				"ListOfData");
		GXDLMSTranslator.addTag(list, TranslatorTags.SUCCESS, "Success");
		GXDLMSTranslator.addTag(list, TranslatorTags.DATA_ACCESS_ERROR,
				"DataAccessError");
		GXDLMSTranslator.addTag(list, TranslatorTags.ATTRIBUTE_DESCRIPTOR,
				"AttributeDescriptor");
		GXDLMSTranslator.addTag(list, TranslatorTags.CLASS_ID, "ClassId");
		GXDLMSTranslator.addTag(list, TranslatorTags.INSTANCE_ID, "InstanceId");
		GXDLMSTranslator.addTag(list, TranslatorTags.ATTRIBUTE_ID,
				"AttributeId");
		GXDLMSTranslator.addTag(list,
				TranslatorTags.METHOD_INVOCATION_PARAMETERS,
				"MethodInvocationParameters");
		GXDLMSTranslator.addTag(list, TranslatorTags.SELECTOR, "Selector");
		GXDLMSTranslator.addTag(list, TranslatorTags.PARAMETER, "Parameter");
		GXDLMSTranslator.addTag(list, TranslatorTags.LAST_BLOCK, "LastBlock");
		GXDLMSTranslator.addTag(list, TranslatorTags.BLOCK_NUMBER,
				"BlockNumber");
		GXDLMSTranslator.addTag(list, TranslatorTags.RAW_DATA, "RawData");
		GXDLMSTranslator.addTag(list, TranslatorTags.METHOD_DESCRIPTOR,
				"MethodDescriptor");
		GXDLMSTranslator.addTag(list, TranslatorTags.METHOD_ID, "MethodId");
		GXDLMSTranslator.addTag(list, TranslatorTags.RESULT, "Result");
		GXDLMSTranslator.addTag(list, TranslatorTags.RETURN_PARAMETERS,
				"ReturnParameters");
		GXDLMSTranslator.addTag(list, TranslatorTags.ACCESS_SELECTION,
				"AccessSelection");
		GXDLMSTranslator.addTag(list, TranslatorTags.VALUE, "Value");
		GXDLMSTranslator.addTag(list, TranslatorTags.ACCESS_SELECTOR,
				"AccessSelector");
		GXDLMSTranslator.addTag(list, TranslatorTags.ACCESS_PARAMETERS,
				"AccessParameters");
		GXDLMSTranslator.addTag(list, TranslatorTags.ATTRIBUTE_DESCRIPTOR_LIST,
				"AttributeDescriptorList");
		GXDLMSTranslator.addTag(list,
				TranslatorTags.ATTRIBUTE_DESCRIPTOR_WITH_SELECTION,
				"AttributeDescriptorWithSelection");
		GXDLMSTranslator.addTag(list, TranslatorTags.READ_DATA_BLOCK_ACCESS,
				"ReadDataBlockAccess");
		GXDLMSTranslator.addTag(list, TranslatorTags.WRITE_DATA_BLOCK_ACCESS,
				"WriteDataBlockAccess");
		GXDLMSTranslator.addTag(list, TranslatorTags.DATA, "Data");
		GXDLMSTranslator.addTag(list, TranslatorTags.INVOKE_ID,
				"InvokeIdAndPriority");
		GXDLMSTranslator.addTag(list, TranslatorTags.LONG_INVOKE_ID,
				"LongInvokeIdAndPriority");
		GXDLMSTranslator.addTag(list, TranslatorTags.DATE_TIME, "DateTime");
		GXDLMSTranslator.addTag(list, TranslatorTags.REASON, "Reason");
		GXDLMSTranslator.addTag(list, TranslatorTags.NOTIFICATION_BODY,
				"NotificationBody");
		GXDLMSTranslator.addTag(list, TranslatorTags.DATA_VALUE, "DataValue");
	}

	static void getDataTypeTags(final Hashtable list) {
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.NONE.getValue(), "None");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.ARRAY.getValue(), "Array");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.BCD.getValue(), "BCD");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.BITSTRING.getValue(),
				"BitString");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.BOOLEAN.getValue(),
				"Boolean");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.COMPACT_ARRAY.getValue(),
				"CompactArray");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.DATE.getValue(), "Date");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.DATETIME.getValue(),
				"DateTime");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.ENUM.getValue(), "Enum");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.FLOAT32.getValue(),
				"Float32");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.FLOAT64.getValue(),
				"Float64");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.INT16.getValue(), "Int16");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.INT32.getValue(), "Int32");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.INT64.getValue(), "Int64");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.INT8.getValue(), "Int8");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.OCTET_STRING.getValue(),
				"OctetString");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.STRING.getValue(), "String");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.STRING_UTF8.getValue(),
				"StringUTF8");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.STRUCTURE.getValue(),
				"Structure");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.TIME.getValue(), "Time");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.UINT16.getValue(), "UInt16");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.UINT32.getValue(), "UInt32");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.UINT64.getValue(), "UInt64");
		GXDLMSTranslator.addTag(list,
				GXDLMS.DATA_TYPE_OFFSET + DataType.UINT8.getValue(), "UInt8");
	}

	static String errorCodeToString(final ErrorCode value) {
		String str;
		switch (value.getValue()) {
		case 13:// ErrorCode.ACCESS_VIOLATED:
			str = "AccessViolated";
			break;
		case 19:// ErrorCode.DATA_BLOCK_NUMBER_INVALID:
			str = "DataBlockNumberInvalid";
			break;
		case 14:// ErrorCode.DATA_BLOCK_UNAVAILABLE:
			str = "DataBlockUnavailable";
			break;
		case 1:// ErrorCode.HARDWARE_FAULT:
			str = "HardwareFault";
			break;
		case 9:// ErrorCode.INCONSISTENT_CLASS:
			str = "InconsistentClass";
			break;
		case 15:// ErrorCode.LONG_GET_OR_READ_ABORTED:
			str = "LongGetOrReadAborted";
			break;
		case 17:// ErrorCode.LONG_SET_OR_WRITE_ABORTED:
			str = "LongSetOrWriteAborted";
			break;
		case 16:// ErrorCode.NO_LONG_GET_OR_READ_IN_PROGRESS:
			str = "NoLongGetOrReadInProgress";
			break;
		case 18:// ErrorCode.NO_LONG_SET_OR_WRITE_IN_PROGRESS:
			str = "NoLongSetOrWriteInProgress";
			break;
		case 0:// ErrorCode.OK:
			str = "Success";
			break;
		case 250:// ErrorCode.OTHER_REASON:
			str = "OtherReason";
			break;
		case 3:// ErrorCode.READ_WRITE_DENIED:
			str = "ReadWriteDenied";
			break;
		case 2:// ErrorCode.TEMPORARY_FAILURE:
			str = "TemporaryFailure";
			break;
		case 11:// ErrorCode.UNAVAILABLE_OBJECT:
			str = "UnavailableObject";
			break;
		case 4:// ErrorCode.UNDEFINED_OBJECT:
			str = "UndefinedObject";
			break;
		case 12:// ErrorCode.UNMATCHED_TYPE:
			str = "UnmatchedType";
			break;
		default:
			throw new IllegalArgumentException(
					"Error code: " + String.valueOf(value));
		}
		return str;
	}

	static ErrorCode valueOfErrorCode(final String value) {
		ErrorCode v;
		if ("AccessViolated".equalsIgnoreCase(value)) {
			v = ErrorCode.ACCESS_VIOLATED;
		} else if ("DataBlockNumberInvalid".equalsIgnoreCase(value)) {
			v = ErrorCode.DATA_BLOCK_NUMBER_INVALID;
		} else if ("DataBlockUnavailable".equalsIgnoreCase(value)) {
			v = ErrorCode.DATA_BLOCK_UNAVAILABLE;
		} else if ("HardwareFault".equalsIgnoreCase(value)) {
			v = ErrorCode.HARDWARE_FAULT;
		} else if ("InconsistentClass".equalsIgnoreCase(value)) {
			v = ErrorCode.INCONSISTENT_CLASS;
		} else if ("LongGetOrReadAborted".equalsIgnoreCase(value)) {
			v = ErrorCode.LONG_GET_OR_READ_ABORTED;
		} else if ("LongSetOrWriteAborted".equalsIgnoreCase(value)) {
			v = ErrorCode.LONG_SET_OR_WRITE_ABORTED;
		} else if ("NoLongGetOrReadInProgress".equalsIgnoreCase(value)) {
			v = ErrorCode.NO_LONG_GET_OR_READ_IN_PROGRESS;
		} else if ("NoLongSetOrWriteInProgress".equalsIgnoreCase(value)) {
			v = ErrorCode.NO_LONG_SET_OR_WRITE_IN_PROGRESS;
		} else if ("Success".equalsIgnoreCase(value)) {
			v = ErrorCode.OK;
		} else if ("OtherReason".equalsIgnoreCase(value)) {
			v = ErrorCode.OTHER_REASON;
		} else if ("ReadWriteDenied".equalsIgnoreCase(value)) {
			v = ErrorCode.READ_WRITE_DENIED;
		} else if ("TemporaryFailure".equalsIgnoreCase(value)) {
			v = ErrorCode.TEMPORARY_FAILURE;
		} else if ("UnavailableObject".equalsIgnoreCase(value)) {
			v = ErrorCode.UNAVAILABLE_OBJECT;
		} else if ("UndefinedObject".equalsIgnoreCase(value)) {
			v = ErrorCode.UNDEFINED_OBJECT;
		} else if ("UnmatchedType".equalsIgnoreCase(value)) {
			v = ErrorCode.UNMATCHED_TYPE;
		} else {
			throw new IllegalArgumentException("Error code: " + value);
		}
		return v;
	}

}
