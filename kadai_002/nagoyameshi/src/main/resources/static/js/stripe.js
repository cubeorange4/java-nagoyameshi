const stripe = Stripe('pk_test_51OIirdA78AZNO6DHImHH0WDGccPl73dEZ6Eabh07lmD2bVZi16UgevE5Au4S1PAsioC2iClmwelPCIoULkOmDLCv00jQjctFSX');
 const paymentButton = document.querySelector('#paymentButton');
 
 paymentButton.addEventListener('click', () => {
   stripe.redirectToCheckout({
     sessionId: sessionId
   })
 });