import { useState , useEffect } from "react";

const MessageDisplay = ({message , type='error', onDismiss})=>{

    useEffect (() =>{
        const timer = setTimeout (()=>{
            onDismiss ();

        }, 4000)
        
    return () => clearTimeout (timer);
    } , [message , onDismiss]);
    
    // dont render if there is no message
    if(!message) return null ; 

    const iserror = type === 'error' ;
    const displayClass = iserror ? 'error-display' : 'success-display' ; 
    const messageClass = iserror ? 'error-message' : 'success-message' ; 
    const progressClass = iserror ? 'error-progress' : 'success-progress' ; 

    return(

        <div className={`message-display ${displayClass}`}>

            <div className="message-content">
                <span className={`message-test ${messageClass}`}>{message}</span>

                <div className={`message-progress ${progressClass}`}></div>
            </div>
        </div>
    ) ; 

} ; 



 export const useMessage = () => {
    const [errorMessage , setErrorMessage] = useState(null) ; 
    const [succesMessage , setSuccesMessage] = useState(null) ; 

    const showError = (message) => {
        setErrorMessage(message)
    }

    const showSuccess = (message) =>{
        setSuccesMessage(message)
    }

    const dismissError = ()=>{
        setErrorMessage(null)
    }

     const dismissSuccess = ()=>{
        setSuccesMessage(null)
    }


    return{
        //component to render the error //
        ErrorDisplay : () => 
        (
            <MessageDisplay 
            message = {errorMessage}
            type="error"
            onDismiss={dismissError}
            />
        )
    , 
    //component to render the succes //
        SuccessDisplay : () => 
        (
            <MessageDisplay 
            message = {succesMessage}
            type="success"
            onDismiss={dismissSuccess}
            />
        ),
    showError , 
    showSuccess,
    dismissError,
    dismissSuccess 
    }; 
   
   
    
}
export default MessageDisplay ; 
