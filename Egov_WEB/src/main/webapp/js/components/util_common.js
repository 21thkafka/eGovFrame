
function getbrowservs()
{
	
    var pieces = navigator.userAgent.match(/Chrom(?:e|ium)\/([0-9]+)\.([0-9]+)\.([0-9]+)\.([0-9]+)/);
    if (pieces == null || pieces.length != 5) {
        return false;
    }
    pieces = pieces.map(piece => parseInt(piece, 10));
    
    var chromeversion = pieces[1];
    
    // Chrome 31이상,IE Edge Check
    if((chromeversion>=31)||(/Edge/.test(navigator.userAgent)))
    {
    	return true;
    }
    else
    {
    	return false;
    }
    
}