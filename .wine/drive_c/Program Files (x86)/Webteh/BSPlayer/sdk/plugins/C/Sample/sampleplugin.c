#define  WIN32_LEAN_AND_MEAN
#include <windows.h>

#include "..\..\..\bsp.h"
#include "..\..\bspplg.h"

PpluginInfo pInfo;

BOOL APIENTRY DllMain( HANDLE hModule, 
                       DWORD  ul_reason_for_call, 
                       LPVOID lpReserved
					 )
{
    return TRUE;
}

void Config(const HWND winh)
{
  MessageBox(winh,"Configuration","Info",0);
}

int HandleKeyPress(const int key,const int other)
{  
 if (key==VK_SPACE)
 {
  MessageBox(pInfo->hwndParent,"Space key was pressed","Info",0);
  return 1;//Cancel default key processing
 } else
 if ((key==VK_F1) && (other & keyShift) && (other & keyControl))
 {
  MessageBox(pInfo->hwndParent,"Shift+Control+F1 was pressed","Info",0);
 } else
 if ((key==VK_F1) && (other & keyShift))
 {
  MessageBox(pInfo->hwndParent,"Shift+F1 was pressed","Info",0);
 }
 return 0;
}

int __stdcall CallbackProc(const DWORD cmdID,void * param1, void * param2)
{
	switch (cmdID) {
		case EV_UNLOAD:MessageBox(pInfo->hwndParent,"Plugin is unloading","Info",0);break;
		case EV_PLAY:pInfo->ShowOSDText("Hello from plugin",4000,50000);break;
		case EV_CONFIG:Config((HWND)param1);break;
		case EV_ABOUT:MessageBox((HWND)param1,"Sample plugin v 0.1","About",0);break;
		case EV_KEY_PRESS:return HandleKeyPress((int)param1,(int)param2);break;
	}

	return 0;
}

#ifdef __cplusplus
extern "C" {
#endif

BOOL __stdcall pluginInit(PpluginInfo plgInfo)
{
  strcpy(plgInfo->description,"Sample plugin");
  plgInfo->plgCallback=CallbackProc;
  pInfo=plgInfo;
  MessageBox(plgInfo->hwndParent,"Plugin init","Info",0);
  return TRUE;
}

//v2.11+
int __stdcall pluginVersion()
{
 return BSP_PLUGIN_VER;
}

#ifdef __cplusplus
}
#endif
